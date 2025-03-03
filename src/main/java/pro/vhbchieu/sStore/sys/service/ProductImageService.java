package pro.vhbchieu.sStore.sys.service;

import pro.vhbchieu.sStore.sys.domain.dto.ProductImageDto;
import java.util.List;

public interface ProductImageService {
    ProductImageDto save(ProductImageDto productImageDto);
    ProductImageDto findById(Long id);
    List<ProductImageDto> findAll();
    void deleteById(Long id);
    List<ProductImageDto> findByProductVariantId(Long productVariantId);
}
