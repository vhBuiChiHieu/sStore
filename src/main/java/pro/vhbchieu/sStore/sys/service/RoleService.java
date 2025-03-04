package pro.vhbchieu.sStore.sys.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.RoleRequest;
import pro.vhbchieu.sStore.sys.domain.dto.account.RoleDto;

import java.util.List;

@Service
public interface RoleService {

    void delete(Long roleId);

    List<RoleDto> getList();

    RoleDto getDetail(Long roleId);

    void create(@Valid RoleRequest request);

    void update(RoleRequest request);

    PageDto<RoleDto> getPage(@Min(1) Integer pageIndex, @Min(1) Integer pageSize);
}
