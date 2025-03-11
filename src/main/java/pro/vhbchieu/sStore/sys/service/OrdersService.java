package pro.vhbchieu.sStore.sys.service;

import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.sys.domain.dto.order.*;

@Service
public interface OrdersService {
    PageDto<OrdersDto> getPage(@Min(1) Integer pageIndex, @Min(1) Integer pageSize);

    void addItem(Long orderId, OrdersItemRequest request);

    void create(OrdersRequest request);

    OrdersDetailDto getDetail(Long orderId);

    void delete(Long orderId);

    void update(Long orderId, OrdersUpdateRequest request);
}
