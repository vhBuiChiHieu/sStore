package pro.vhbchieu.sStore.sys.domain.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersItemRequest {
    private Long productVariantId;
    private Double price;
    private Integer quantity;
}
