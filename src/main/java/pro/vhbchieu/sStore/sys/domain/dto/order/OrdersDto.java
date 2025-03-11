package pro.vhbchieu.sStore.sys.domain.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.vhbchieu.sStore.sys.domain.entity.order.Orders;

@Getter
@Setter
@NoArgsConstructor
public class OrdersDto {
    protected Long id;
    protected Long accountId;
    protected String status;
    protected Double total;
    protected String paymentMethod;
    protected String shippingAddress;

    public OrdersDto(Orders order) {
        this.id = order.getId();
        this.accountId = order.getAccount().getId();
        this.status = order.getStatus();
        this.total = order.getTotal();
        this.paymentMethod = order.getPaymentMethod();
        this.shippingAddress = order.getShippingAddress();
    }
}
