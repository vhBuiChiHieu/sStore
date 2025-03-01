package pro.vhbchieu.sStore.sys.service;

import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.sys.domain.dto.product.ProductVariantDetail;

@Service
public interface ProductVariantService {
    ProductVariantDetail getDetail(Long variantId);

    void updateStock(Long variantId, Integer sl);

    void delete(Long variantId);
}
