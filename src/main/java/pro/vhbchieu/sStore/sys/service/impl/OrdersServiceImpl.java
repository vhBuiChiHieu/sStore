package pro.vhbchieu.sStore.sys.service.impl;

import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.config.constant.OrderStatus;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.order.*;
import pro.vhbchieu.sStore.sys.domain.entity.Account;
import pro.vhbchieu.sStore.sys.domain.entity.order.OrderItem;
import pro.vhbchieu.sStore.sys.domain.entity.order.Orders;
import pro.vhbchieu.sStore.sys.domain.entity.product.ProductVariant;
import pro.vhbchieu.sStore.sys.repository.AccountRepository;
import pro.vhbchieu.sStore.sys.repository.OrdersItemRepo;
import pro.vhbchieu.sStore.sys.repository.OrdersRepository;
import pro.vhbchieu.sStore.sys.repository.ProductVariantRepository;
import pro.vhbchieu.sStore.sys.service.OrdersService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersItemRepo ordersItemRepo;
    private final AccountRepository accountRepository;
    private final ProductVariantRepository productVariantRepository;

    @Override
    public PageDto<OrdersDto> getPage(Integer pageIndex, Integer pageSize) {
        Specification<Orders> specification = (r, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            return cb.and(predicates.toArray(new Predicate[0]
            ));
        };

        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);

        Page<OrdersDto> orderDtoPage = ordersRepository.findAll(pageable).map(OrdersDto::new);

        return PageDto.of(orderDtoPage);
    }

    @Override
    public void addItem(Long orderId, OrdersItemRequest request) {
        Orders orders = ordersRepository.findById(orderId).orElseThrow(
                () -> new CustomException(ErrorContent.ORDER_NOT_FOUND)
        );

        OrderItem item = OrderItem.builder()
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
        ordersItemRepo.save(item);

        orders.getOrderItems().add(item);
        ordersRepository.save(orders);
    }

    @Override
    public void create(OrdersRequest request) {
        Account account = accountRepository.findById(request.getAccountId()).orElseThrow(
                () -> new CustomException(ErrorContent.ACCOUNT_NOT_EXIST)
        );

        Orders newOrder = new Orders();
        newOrder.setAccount(account);
        newOrder.setPaymentMethod(request.getPaymentMethod());
        newOrder.setShippingAddress(request.getShippingAddress());
        newOrder.setStatus(OrderStatus.PENDING.name());
        newOrder.setTotal(0d);
        ordersRepository.save(newOrder);

        Map<Long, ProductVariant> checkedVariants = new HashMap<>();
        for (OrdersItemRequest item : request.getItems()) {
            ProductVariant variant = productVariantRepository.findById(item.getProductVariantId()).orElseThrow(
                    () -> new CustomException(ErrorContent.PRODUCT_VARIANT_NOT_EXIST)
            );

            if (variant.getStock() < item.getQuantity()) {
                throw new CustomException(ErrorContent.STOCK_NOT_ENOUGH);
            }

            checkedVariants.put(variant.getId(), variant);
        }

        request.getItems().forEach(item -> {
            ProductVariant variant = checkedVariants.get(item.getProductVariantId());

            OrderItem orderItem = OrderItem.builder()
                    .productVariant(variant)
                    .order(newOrder)
                    .price(item.getPrice())
                    .quantity(item.getQuantity())
                    .build();
            ordersItemRepo.save(orderItem);

            variant.setStock(variant.getStock() - item.getQuantity());
            productVariantRepository.save(variant);

            newOrder.setTotal(newOrder.getTotal() + item.getPrice() * item.getQuantity());
            newOrder.getOrderItems().add(orderItem);
        });

        ordersRepository.save(newOrder);
    }

    @Override
    public OrdersDetailDto getDetail(Long orderId) {
        Orders orders = ordersRepository.findById(orderId).orElseThrow(
                () -> new CustomException(ErrorContent.ORDER_NOT_FOUND)
        );
        return new OrdersDetailDto(orders);
    }

    @Override
    public void delete(Long orderId) {
        ordersItemRepo.deleteAllByOrder_Id(orderId);
        ordersRepository.deleteById(orderId);
    }

    @Override
    public void update(Long orderId, OrdersUpdateRequest request) {
        Orders orders = ordersRepository.findById(orderId).orElseThrow(
                () -> new CustomException(ErrorContent.ORDER_NOT_FOUND)
        );

        orders.setStatus(request.getStatus());
        orders.setShippingAddress(request.getShippingAddress());
        orders.setPaymentMethod(request.getPaymentMethod());

        if (request.getStatus().equals(OrderStatus.CANCELLED.name())) {
            orders.getOrderItems().forEach(item -> {
                ProductVariant variant = item.getProductVariant();
                variant.setStock(variant.getStock() + item.getQuantity());
                productVariantRepository.save(variant);
            });
        }

        ordersRepository.save(orders);
    }

}
