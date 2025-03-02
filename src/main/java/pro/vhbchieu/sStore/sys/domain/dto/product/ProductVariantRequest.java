package pro.vhbchieu.sStore.sys.domain.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductVariantRequest {
    private String color;
    private String size;
    private String sku;
    private Double price;
    private Integer stock;
}
