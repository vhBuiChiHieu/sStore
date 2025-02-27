package pro.vhbchieu.sStore.sys.domain.entity.order;

import jakarta.persistence.*;
import lombok.*;
import pro.vhbchieu.sStore.sys.domain.entity.BaseEntity;
import pro.vhbchieu.sStore.sys.domain.entity.product.ProductVariant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_item")
public class CartItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;

    private Integer quantity;
}
