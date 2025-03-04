package pro.vhbchieu.sStore.sys.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.order.CartDto;
import pro.vhbchieu.sStore.sys.domain.entity.order.Cart;
import pro.vhbchieu.sStore.sys.repository.CartRepository;
import pro.vhbchieu.sStore.sys.service.CartService;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public CartDto getDetail(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(
                () -> new CustomException(ErrorContent.CART_NOT_FOUND)
        );
        return new CartDto(cart);
    }

    @Override
    public PageDto<CartDto> getPage(Integer pageIndex, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        return PageDto.of(cartRepository.findAll(pageable).map(CartDto::new));
    }
}
