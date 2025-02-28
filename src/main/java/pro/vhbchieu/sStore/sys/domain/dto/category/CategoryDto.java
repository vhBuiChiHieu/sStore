package pro.vhbchieu.sStore.sys.domain.dto.category;

import lombok.*;
import pro.vhbchieu.sStore.sys.domain.entity.product.Category;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private String slug;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.slug = category.getSlug();
    }
}
