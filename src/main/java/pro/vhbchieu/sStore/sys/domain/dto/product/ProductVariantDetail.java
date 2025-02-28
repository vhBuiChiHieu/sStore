package pro.vhbchieu.sStore.sys.domain.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.vhbchieu.sStore.sys.domain.entity.product.ProductVariant;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductVariantDetail extends ProductVariantDto{

    private List<ProductImageDto> productImages = new ArrayList<>();

    public ProductVariantDetail(ProductVariant productVariant) {
        super(productVariant);
        this.productImages = productVariant.getProductImages().stream().map(ProductImageDto::new).toList();
    }
}
