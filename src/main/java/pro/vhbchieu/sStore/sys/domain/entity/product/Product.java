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
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductVariant productVariant;
}
