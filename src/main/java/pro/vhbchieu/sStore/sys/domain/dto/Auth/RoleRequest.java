package pro.vhbchieu.sStore.sys.domain.dto.Auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import pro.vhbchieu.sStore.config.constant.RoleType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RoleRequest {
    private Long id;
    private String name;
    private String description;
    private List<PermissionRequest> permissions;
}
