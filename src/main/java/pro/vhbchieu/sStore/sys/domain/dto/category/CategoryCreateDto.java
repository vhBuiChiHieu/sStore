package pro.vhbchieu.sStore.sys.domain.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateDto {

    @NotBlank(message = "Name must not be blank")
    private String name;
    private String description;
}
