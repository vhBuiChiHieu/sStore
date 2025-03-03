package pro.vhbchieu.sStore.sys.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.ProductImageDto;
import pro.vhbchieu.sStore.sys.domain.entity.product.ProductImage;
import pro.vhbchieu.sStore.sys.repository.ProductImageRepository;
import pro.vhbchieu.sStore.sys.service.ProductImageService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository productImageRepository;

    @Override
    public ProductImageDto save(ProductImageDto productImageDto) {
        if (productImageDto == null) {
            throw new CustomException(ErrorContent.PRODUCT_IMAGE_INVALID);
        }
        
        ProductImage entity = new ProductImage();
        if (productImageDto.getId() != null) {
            entity = productImageRepository.findById(productImageDto.getId())
                    .orElseThrow(() -> new CustomException(ErrorContent.PRODUCT_IMAGE_NOT_FOUND));
        }
        
        entity.setImageUrl(productImageDto.getImageUrl());
        entity.setIsPrimary(productImageDto.getIsPrimary());
        
        return new ProductImageDto(productImageRepository.save(entity));
    }

    @Override
    public ProductImageDto findById(Long id) {
        if (id == null) {
            throw new CustomException(ErrorContent.PRODUCT_IMAGE_ID_REQUIRED);
        }
        return productImageRepository.findById(id)
                .map(ProductImageDto::new)
                .orElseThrow(() -> new CustomException(ErrorContent.PRODUCT_IMAGE_NOT_FOUND));
    }

    @Override
    public List<ProductImageDto> findAll() {
        return productImageRepository.findAll().stream()
                .map(ProductImageDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new CustomException(ErrorContent.PRODUCT_IMAGE_ID_REQUIRED);
        }
        if (!productImageRepository.existsById(id)) {
            throw new CustomException(ErrorContent.PRODUCT_IMAGE_NOT_FOUND);
        }
        productImageRepository.deleteById(id);
    }

    @Override
    public List<ProductImageDto> findByProductVariantId(Long productVariantId) {
        if (productVariantId == null) {
            throw new CustomException(ErrorContent.PRODUCT_VARIANT_ID_REQUIRED);
        }
        return productImageRepository.findByProductVariantId(productVariantId).stream()
                .map(ProductImageDto::new)
                .collect(Collectors.toList());
    }
}
