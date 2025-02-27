package pro.vhbchieu.sStore.sys.domain.entity.product;

import jakarta.persistence.*;
import lombok.*;
import pro.vhbchieu.sStore.sys.domain.entity.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_image")
public class ProductImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;

    @Column(nullable = false)
    private String imageUrl;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isPrimary = false;

}
