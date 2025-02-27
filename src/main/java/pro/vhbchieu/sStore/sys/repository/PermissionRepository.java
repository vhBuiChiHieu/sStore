package pro.vhbchieu.sStore.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.vhbchieu.sStore.sys.domain.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
