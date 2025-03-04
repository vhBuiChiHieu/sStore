package pro.vhbchieu.sStore.sys.service;

import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.PermissionRequest;
import pro.vhbchieu.sStore.sys.domain.dto.account.PermissionDto;

import java.util.List;

@Service
public interface PermissionService {
    List<PermissionDto> getList();

    PermissionDto create(PermissionRequest request);

    void delete(Long permissionId);

    PageDto<PermissionDto> getPage(Integer pageIndex, Integer pageSize);
}
