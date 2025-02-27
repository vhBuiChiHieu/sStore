package pro.vhbchieu.sStore.sys.domain.entity.order;

import jakarta.persistence.*;
import lombok.*;
import pro.vhbchieu.sStore.config.constant.OrderStatus;
import pro.vhbchieu.sStore.sys.domain.entity.Account;
import pro.vhbchieu.sStore.sys.domain.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Orders extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = false)
    private Double total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String paymentMethod;

    private String shippingAddress;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

}
