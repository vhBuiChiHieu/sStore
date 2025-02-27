package pro.vhbchieu.sStore.sys.domain.entity.order;

import jakarta.persistence.*;
import lombok.*;
import pro.vhbchieu.sStore.sys.domain.entity.product.ProductVariant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;
}
