package pro.vhbchieu.sStore.sys.domain.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersUpdateRequest extends OrdersRequest{
    private String status;
    private Double total;
}
