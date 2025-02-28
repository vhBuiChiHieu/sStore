package pro.vhbchieu.sStore.sys.domain.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.vhbchieu.sStore.sys.domain.entity.product.ProductVariant;

@Getter
@Setter
@NoArgsConstructor
public class ProductVariantDto {
    protected Long id;
    protected String color;
    protected String size;
    protected String sku;
    protected Double price;
    protected Integer stock;

    public ProductVariantDto(ProductVariant variant) {
        this.id = variant.getId();
        this.color = variant.getColor();
        this.size = variant.getSize();
        this.sku = variant.getSku();
        this.price = variant.getPrice();
        this.stock = variant.getStock();
    }
}
