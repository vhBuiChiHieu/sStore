package pro.vhbchieu.sStore.sys.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.PermissionRequest;
import pro.vhbchieu.sStore.sys.domain.dto.account.PermissionDto;
import pro.vhbchieu.sStore.sys.domain.entity.Permission;
import pro.vhbchieu.sStore.sys.repository.PermissionRepository;
import pro.vhbchieu.sStore.sys.service.PermissionService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public List<PermissionDto> getList() {
        return permissionRepository.findAll().stream().map(PermissionDto::new).toList();
    }

    @Override
    public PermissionDto create(PermissionRequest request) {
        Permission newPermission = Permission.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        return new PermissionDto(permissionRepository.save(newPermission));
    }

    @Override
    public void delete(Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId).orElse(null);
        if (permission != null) {
            permission.getRoles().forEach(role -> role.getPermissions().remove(permission));
            permission.getRoles().clear();
            permissionRepository.delete(permission);
        }
    }

    @Override
    public PageDto<PermissionDto> getPage(Integer pageIndex, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        return PageDto.of(permissionRepository.findAll(pageable).map(PermissionDto::new));
    }

}
