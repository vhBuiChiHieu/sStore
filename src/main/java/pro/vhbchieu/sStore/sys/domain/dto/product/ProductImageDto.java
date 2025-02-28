package pro.vhbchieu.sStore.sys.domain.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.vhbchieu.sStore.sys.domain.entity.product.ProductImage;

@Getter
@Setter
@NoArgsConstructor
public class ProductImageDto {
    private Long id;
    private String imageUrl;
    private Boolean isPrimary;

    public ProductImageDto(ProductImage productImage) {
        this.id = productImage.getId();
        this.imageUrl = productImage.getImageUrl();
        this.isPrimary = productImage.getIsPrimary();
    }
}
