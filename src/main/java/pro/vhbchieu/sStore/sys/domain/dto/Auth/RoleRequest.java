package pro.vhbchieu.sStore.sys.domain.dto.Auth;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleRequest {
    private Long id;
    private String name;
    private String description;
    private List<PermissionRequest> permissions;
}
