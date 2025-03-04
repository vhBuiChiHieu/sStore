package pro.vhbchieu.sStore.sys.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.PermissionRequest;
import pro.vhbchieu.sStore.sys.domain.dto.account.PermissionDto;
import pro.vhbchieu.sStore.sys.service.PermissionService;

import java.util.List;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping()
    public List<PermissionDto> getList(){
        return permissionService.getList();
    }

    @GetMapping("/page")
    public PageDto<PermissionDto> getPage(
            @Min(1) @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @Min(1) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return permissionService.getPage(pageIndex, pageSize);
    }

    @PostMapping()
    public PermissionDto create(@RequestBody PermissionRequest request) {
        return permissionService.create(request);
    }

    @DeleteMapping("/{permissionId}")
    public void delete(@PathVariable("permissionId") Long permissionId) {
        permissionService.delete(permissionId);
    }

}
