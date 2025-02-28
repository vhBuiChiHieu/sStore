package pro.vhbchieu.sStore.sys.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pro.vhbchieu.sStore.sys.domain.dto.Brand.BrandDto;
import pro.vhbchieu.sStore.sys.domain.dto.Brand.BrandRequest;
import pro.vhbchieu.sStore.sys.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class BrandController {

    private final BrandService brandService;

    @GetMapping()
    public List<BrandDto> getList() {
        return brandService.getList();
    }

    @PostMapping()
    public BrandDto create(@RequestBody BrandRequest request) {
        return brandService.create(request);
    }

    @PutMapping("/{brandId}")
    public void update(@RequestBody BrandRequest request, @PathVariable Long brandId) {
        brandService.update(brandId, request);
    }

    @DeleteMapping("/{brandId}")
    public void delete(@PathVariable Long brandId) {
        brandService.delete(brandId);
    }


}
