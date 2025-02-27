package pro.vhbchieu.sStore.sys.domain.dto.account;

import lombok.*;
import pro.vhbchieu.sStore.sys.domain.entity.Permission;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionDto {
    private Long id;
    private String name;
    private String description;

    public PermissionDto(Permission permission) {
        this.id = permission.getId();
        this.name = permission.getName().name();
        this.description = permission.getDescription();
    }
}
