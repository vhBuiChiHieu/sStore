package pro.vhbchieu.sStore.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.vhbchieu.sStore.sys.domain.entity.order.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
