package pro.vhbchieu.sStore.sys.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import pro.vhbchieu.sStore.config.constant.PermissionType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Table(
        name = "permission",
        indexes = {
                @Index(name = "idx_permission_name", columnList = "name", unique = true)
        }
)
public class Permission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PermissionType name;

    private String description;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Role> roles = new ArrayList<>();
}
