package pro.vhbchieu.sStore.sys.domain.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.vhbchieu.sStore.sys.domain.entity.order.Orders;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrdersDetailDto extends OrdersDto{

    private List<OrdersItemDto> items = new ArrayList<>();

    public OrdersDetailDto(Orders order) {
        super(order);
        this.items = order.getOrderItems().stream().map(OrdersItemDto::new).toList();
    }
}
