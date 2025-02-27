package pro.vhbchieu.sStore.sys.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import pro.vhbchieu.sStore.config.constant.AccountStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder()
@ToString
@Table(
        name = "account",
        indexes = {
                @Index(name = "idx_account_mail", columnList = "mail", unique = true)
        }
)
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String mail;
    private String hashPassword;
    private String phone;

    @Enumerated(EnumType.ORDINAL)
    private AccountStatus status;

    @ManyToMany()
    @JoinTable(
            name = "account_has_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private List<Role> roles = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;
}

