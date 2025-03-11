package pro.vhbchieu.sStore.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.vhbchieu.sStore.sys.domain.entity.order.OrderItem;

@Repository
public interface OrdersItemRepo extends JpaRepository<OrderItem, Long> {
    void deleteAllByOrder_Id(Long orderId);
}
