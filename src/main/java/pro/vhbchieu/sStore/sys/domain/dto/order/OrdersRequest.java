package pro.vhbchieu.sStore.sys.domain.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrdersRequest {
    protected Long accountId;
    protected List<OrdersItemRequest> items;
    protected String shippingAddress;
    protected String paymentMethod;
}
