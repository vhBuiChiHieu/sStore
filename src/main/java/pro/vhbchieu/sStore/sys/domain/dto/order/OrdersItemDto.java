package pro.vhbchieu.sStore.sys.domain.dto.order;

import lombok.Getter;
import lombok.Setter;
import pro.vhbchieu.sStore.sys.domain.entity.order.OrderItem;

@Getter
@Setter
public class OrdersItemDto {
    private Long id;
    private Long productVariantId;
    private Double price;
    private Integer quantity;

    public OrdersItemDto(OrderItem item) {
        this.id = item.getId();
        this.productVariantId = item.getProductVariant().getId();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
    }
}
