package pro.vhbchieu.sStore.sys.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.RoleRequest;
import pro.vhbchieu.sStore.sys.domain.dto.account.PermissionDto;
import pro.vhbchieu.sStore.sys.domain.dto.account.RoleDto;
import pro.vhbchieu.sStore.sys.domain.entity.Permission;
import pro.vhbchieu.sStore.sys.domain.entity.Role;
import pro.vhbchieu.sStore.sys.repository.PermissionRepository;
import pro.vhbchieu.sStore.sys.repository.RoleRepository;
import pro.vhbchieu.sStore.sys.service.RoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public void delete(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public List<RoleDto> getList() {
        return roleRepository.findAll().stream().map(
                role ->
                        RoleDto.builder()
                                .id(role.getId())
                                .name(role.getName().name())
                                .description(role.getDescription())
                                .build()
        ).toList();
    }

    @Override
    public RoleDto getDetail(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new CustomException(ErrorContent.ROLE_NOT_EXIST)
        );
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName().name())
                .description(role.getDescription())
                .permissions(role.getPermissions().stream().map(PermissionDto::new).toList())
                .build();
    }

    @Override
    public void create(RoleRequest request) {
        List<Permission> permissions = request.getPermissions().stream().map(
                pr -> permissionRepository.findById(pr.getId()).orElse(null)
        ).filter(Objects::nonNull).toList();

        Role newRole = Role.builder()
                .name(request.getName())
                .description(request.getDescription())
                .permissions(permissions)
                .build();
        roleRepository.save(newRole);
    }

    @Override
    public void update(RoleRequest request) {
        Role role = roleRepository.findById(request.getId()).orElseThrow(
                () -> new CustomException(ErrorContent.ROLE_NOT_EXIST)
        );

        List<Permission> permissions = request.getPermissions().stream().map(
                pr -> permissionRepository.findById(pr.getId()).orElse(null)
        ).filter(Objects::nonNull).collect(Collectors.toList());

        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setPermissions(permissions);
        roleRepository.save(role);
    }


}
