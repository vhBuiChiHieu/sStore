package pro.vhbchieu.sStore.sys.domain.dto;

import lombok.*;
import pro.vhbchieu.sStore.sys.domain.entity.product.ProductImage;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageDto {
    private Long id;
    private Long productVariantId;
    private String imageUrl;
    private Boolean isPrimary;

    public ProductImageDto(ProductImage entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.productVariantId = entity.getProductVariant() != null ? entity.getProductVariant().getId() : null;
            this.imageUrl = entity.getImageUrl();
            this.isPrimary = entity.getIsPrimary();
        }
    }
}
