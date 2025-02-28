package pro.vhbchieu.sStore.sys.service;

import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.sys.domain.dto.Brand.BrandDto;
import pro.vhbchieu.sStore.sys.domain.dto.Brand.BrandRequest;

import java.util.List;

@Service
public interface BrandService {
    List<BrandDto> getList();

    BrandDto create(BrandRequest request);

    void update(Long brandId, BrandRequest request);

    void delete(Long brandId);
}
