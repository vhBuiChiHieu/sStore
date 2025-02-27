package pro.vhbchieu.sStore.sys.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import pro.vhbchieu.sStore.config.constant.AccountStatus;
import pro.vhbchieu.sStore.sys.domain.entity.order.Cart;
import pro.vhbchieu.sStore.sys.domain.entity.order.Orders;

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
    private UserInfo userInfo;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Cart cart;

    @Builder.Default
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();
}

