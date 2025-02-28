package pro.vhbchieu.sStore.sys.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.Brand.BrandDto;
import pro.vhbchieu.sStore.sys.domain.dto.Brand.BrandRequest;
import pro.vhbchieu.sStore.sys.domain.entity.product.Brand;
import pro.vhbchieu.sStore.sys.repository.BrandRepository;
import pro.vhbchieu.sStore.sys.service.BrandService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public List<BrandDto> getList() {
        return brandRepository.findAll().stream().map(BrandDto::new).toList();
    }

    @Override
    public BrandDto create(BrandRequest request) {
        Brand newBrand = Brand.builder()
                .name(request.getName())
                .description(request.getDescription())
                .logoUrl(request.getLogoUrl())
                .build();
        return new BrandDto(brandRepository.save(newBrand));
    }

    @Override
    public void update(Long brandId, BrandRequest request) {
        Brand brand = brandRepository.findById(brandId).orElseThrow(
                () -> new CustomException(ErrorContent.BRAND_NOT_EXIST)
        );

        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setLogoUrl(request.getLogoUrl());
        
        brandRepository.save(brand);
    }

    @Override
    public void delete(Long brandId) {
        brandRepository.deleteById(brandId);

    }
}
