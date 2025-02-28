package pro.vhbchieu.sStore.sys.domain.dto.Brand;

import lombok.*;
import pro.vhbchieu.sStore.sys.domain.entity.product.Brand;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandDto {
    private Long id;
    private String name;
    private String description;
    private String logoUrl;

    public BrandDto(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
        this.description = brand.getDescription();
        this.logoUrl = brand.getLogoUrl();
    }
}
