package pro.vhbchieu.sStore.sys.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pro.vhbchieu.sStore.sys.domain.dto.product.ProductVariantDetail;
import pro.vhbchieu.sStore.sys.service.ProductVariantService;

@RestController
@RequestMapping("/variant")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @PreAuthorize("permitAll()")
    @GetMapping("/{variantId}")
    public ProductVariantDetail getDetail(@PathVariable("variantId") Long variantId) {
        return productVariantService.getDetail(variantId);
    }

    @PutMapping("/{variantId}/stock")
    public void updateStock(@PathVariable("variantId") Long variantId, @RequestParam(name = "sl") Integer sl) {
        productVariantService.updateStock(variantId, sl);
    }

    @DeleteMapping("/{variantId}")
    public void delete(@PathVariable("variantId") Long variantId) {
        productVariantService.delete(variantId);
    }

}
