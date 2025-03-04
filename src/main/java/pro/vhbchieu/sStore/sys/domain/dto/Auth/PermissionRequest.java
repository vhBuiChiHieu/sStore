package pro.vhbchieu.sStore.sys.domain.dto.Auth;

import lombok.Getter;
import lombok.Setter;
import pro.vhbchieu.sStore.config.constant.PermissionType;

@Getter
@Setter
public class PermissionRequest {
    private Long id;
    private String name;
    private String description;
}
