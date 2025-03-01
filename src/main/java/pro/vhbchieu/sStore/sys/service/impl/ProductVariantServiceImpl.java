package pro.vhbchieu.sStore.sys.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.product.ProductVariantDetail;
import pro.vhbchieu.sStore.sys.domain.entity.product.ProductVariant;
import pro.vhbchieu.sStore.sys.repository.ProductVariantRepository;
import pro.vhbchieu.sStore.sys.service.ProductVariantService;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;

    @Override
    public ProductVariantDetail getDetail(Long variantId) {
        ProductVariant variant = productVariantRepository.findById(variantId).orElseThrow(
                () -> new CustomException(ErrorContent.PRODUCT_VARIANT_NOT_EXIST)
        );
        return new ProductVariantDetail(variant);
    }

    @Override
    public void updateStock(Long variantId, Integer sl) {
        ProductVariant variant = productVariantRepository.findById(variantId).orElseThrow(
                () -> new CustomException(ErrorContent.PRODUCT_VARIANT_NOT_EXIST)
        );
        variant.setStock(sl);
        productVariantRepository.save(variant);
    }

    @Override
    public void delete(Long variantId) {
        productVariantRepository.deleteById(variantId);
    }


}
