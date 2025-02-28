package pro.vhbchieu.sStore.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.vhbchieu.sStore.sys.domain.entity.product.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
