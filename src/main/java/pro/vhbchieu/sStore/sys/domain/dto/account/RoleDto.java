package pro.vhbchieu.sStore.sys.domain.dto.account;

import lombok.*;
import pro.vhbchieu.sStore.sys.domain.entity.Role;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
    private Long id;
    private String name;
    private String description;
    private Set<PermissionDto> permissions;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName().name();
        this.description = role.getDescription();
        this.permissions = role.getPermissions().stream().map(PermissionDto::new).collect(Collectors.toSet());
    }
}
