package pro.vhbchieu.sStore.sys.domain.entity.product;

import jakarta.persistence.*;
import lombok.*;
import pro.vhbchieu.sStore.sys.domain.entity.BaseEntity;
import pro.vhbchieu.sStore.sys.domain.entity.order.CartItem;
import pro.vhbchieu.sStore.sys.domain.entity.order.OrderItem;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "product_variant",
        indexes = {
            @Index(name = "idx_product_variant_sku", columnList = "sku", unique = true)
        }
)
public class ProductVariant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String color;

    private String sku;

    @Column(nullable = false)
    private Double price;

    @Builder.Default
    private Integer stock = 0;

    @Builder.Default
    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> productImages = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "productVariant", fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "productVariant", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();
}
