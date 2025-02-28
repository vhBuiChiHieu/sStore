package pro.vhbchieu.sStore.sys.domain.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.vhbchieu.sStore.sys.domain.entity.product.Product;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetail extends ProductDto {

    private List<ProductVariantDetail> productVariants = new ArrayList<>();

    public ProductDetail(Product product) {
        super(product);
        this.productVariants = product.getProductVariants().stream().map(ProductVariantDetail::new).toList();
    }

}
