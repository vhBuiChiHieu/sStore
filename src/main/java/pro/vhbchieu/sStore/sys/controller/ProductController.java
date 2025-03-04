package pro.vhbchieu.sStore.sys.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.sys.domain.dto.product.*;
import pro.vhbchieu.sStore.sys.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("permitAll()")
    @GetMapping()
    public List<ProductDto> getList() {
        return productService.getList();
    }

    @GetMapping("/page")
    public PageDto<ProductDto> getPage(
            @Min(1) @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @Min(1) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return productService.getPage(pageIndex, pageSize);
    }

    @PostMapping()
    public ProductDto create(@RequestBody ProductRequest request) {
        return productService.create(request);
    }

    @GetMapping("/{productId}")
    public ProductDetail getDetail(@PathVariable Long productId) {
        return productService.getDetail(productId);
    }

    @PutMapping("/{productId}")
    public void update(@PathVariable Long productId, @RequestBody ProductRequest request) {
        productService.update(productId, request);
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable Long productId) {
        productService.delete(productId);
    }

    //Variant
    @PostMapping("/{productId}/variant")
    public ProductVariantDto createVariant(@PathVariable Long productId, @RequestBody ProductVariantRequest request) {
        return productService.createVariant(productId, request);
    }

}
