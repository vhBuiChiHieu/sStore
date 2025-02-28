package pro.vhbchieu.sStore.sys.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.RoleRequest;
import pro.vhbchieu.sStore.sys.domain.dto.account.RoleDto;
import pro.vhbchieu.sStore.sys.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    private final RoleService roleService;

    @GetMapping()
    public List<RoleDto> getList(){
        return roleService.getList();
    }

    @GetMapping("/{roleId}")
    public RoleDto getDetail(@PathVariable("roleId") Long roleId) {
        return roleService.getDetail(roleId);
    }

    @PostMapping()
    public void create(@RequestBody RoleRequest request) {
        roleService.create(request);
    }

    @DeleteMapping("/{roleId}")
    public void delete(@PathVariable("roleId") Long roleId) {
        roleService.delete(roleId);
    }

    @PutMapping()
    public void update(@RequestBody RoleRequest request) {
        roleService.update(request);
    }

}
