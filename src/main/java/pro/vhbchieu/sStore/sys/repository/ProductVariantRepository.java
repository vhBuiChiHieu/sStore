package pro.vhbchieu.sStore.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.vhbchieu.sStore.sys.domain.entity.product.ProductVariant;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    boolean existsBySku(String sku);
}
