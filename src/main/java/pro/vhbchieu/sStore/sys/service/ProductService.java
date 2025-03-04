package pro.vhbchieu.sStore.sys.service;

import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.sys.domain.dto.product.*;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDto> getList();

    ProductDto create(ProductRequest request);

    ProductDetail getDetail(Long productId);

    void update(Long productId, ProductRequest request);

    void delete(Long productId);

    ProductVariantDto createVariant(Long productId, ProductVariantRequest request);

    PageDto<ProductDto> getPage(@Min(1) Integer pageIndex, @Min(1) Integer pageSize);
}
