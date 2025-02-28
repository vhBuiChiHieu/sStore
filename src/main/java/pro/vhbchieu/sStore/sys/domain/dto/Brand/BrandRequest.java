package pro.vhbchieu.sStore.sys.domain.dto.Brand;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BrandRequest {
    private String name;
    private String description;
    private String logoUrl;
}
