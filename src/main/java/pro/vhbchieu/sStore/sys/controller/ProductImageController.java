package pro.vhbchieu.sStore.sys.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pro.vhbchieu.sStore.sys.domain.dto.ProductImageDto;
import pro.vhbchieu.sStore.sys.service.ProductImageService;

import java.util.List;

@RestController
@RequestMapping("/api/product-variant-images")
@RequiredArgsConstructor
public class ProductImageController {
    private final ProductImageService productImageService;

    @PostMapping
    public ProductImageDto create(@RequestBody ProductImageDto productImageDto) {
        return productImageService.save(productImageDto);
    }

    @GetMapping("/{id}")
    public ProductImageDto findById(@PathVariable Long id) {
        return productImageService.findById(id);
    }

    @GetMapping
    public List<ProductImageDto> findAll() {
        return productImageService.findAll();
    }

    @PutMapping("/{id}")
    public ProductImageDto update(@PathVariable Long id, @RequestBody ProductImageDto productImageDto) {
        productImageDto.setId(id);
        return productImageService.save(productImageDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productImageService.deleteById(id);
    }

    @GetMapping("/by-product-variant/{productVariantId}")
    public List<ProductImageDto> findByProductVariantId(@PathVariable Long productVariantId) {
        return productImageService.findByProductVariantId(productVariantId);
    }
}
