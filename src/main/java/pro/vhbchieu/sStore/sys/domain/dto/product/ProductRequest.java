package pro.vhbchieu.sStore.sys.domain.dto.product;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductRequest {
    private String name;
    private String description;

    @NotNull
    private Long brandId;
    @NotNull
    private Long categoryId;
}
