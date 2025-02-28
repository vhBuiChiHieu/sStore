package pro.vhbchieu.sStore.sys.domain.dto.product;

import lombok.*;
import pro.vhbchieu.sStore.sys.domain.dto.Brand.BrandDto;
import pro.vhbchieu.sStore.sys.domain.dto.category.CategoryDto;
import pro.vhbchieu.sStore.sys.domain.entity.product.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    protected Long id;
    protected String name;
    protected String description;
    protected BrandDto brand;
    protected CategoryDto category;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.brand = new BrandDto(product.getBrand());
        this.category = new CategoryDto(product.getCategory());
    }
}
