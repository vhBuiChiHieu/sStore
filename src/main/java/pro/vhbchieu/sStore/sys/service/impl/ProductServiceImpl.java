package pro.vhbchieu.sStore.sys.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.product.*;
import pro.vhbchieu.sStore.sys.domain.entity.product.Brand;
import pro.vhbchieu.sStore.sys.domain.entity.product.Category;
import pro.vhbchieu.sStore.sys.domain.entity.product.Product;
import pro.vhbchieu.sStore.sys.domain.entity.product.ProductVariant;
import pro.vhbchieu.sStore.sys.repository.BrandRepository;
import pro.vhbchieu.sStore.sys.repository.CategoryRepository;
import pro.vhbchieu.sStore.sys.repository.ProductRepository;
import pro.vhbchieu.sStore.sys.repository.ProductVariantRepository;
import pro.vhbchieu.sStore.sys.service.ProductService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductVariantRepository productVariantRepository;

    @Override
    public List<ProductDto> getList() {
        return productRepository.findAll().stream().map(ProductDto::new).toList();
    }

    @Override
    public ProductDto create(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new CustomException(ErrorContent.CATEGORY_NOT_EXIST)
        );

        Brand brand = brandRepository.findById(request.getBrandId()).orElseThrow(
                () -> new CustomException(ErrorContent.BRAND_NOT_EXIST)
        );

        Product newProduct = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .brand(brand)
                .category(category)
                .build();

        return new ProductDto(productRepository.save(newProduct));
    }

    @Override
    public ProductDetail getDetail(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new CustomException(ErrorContent.PRODUCT_NOT_EXIST)
        );
        return new ProductDetail(product);
    }

    @Override
    public void update(Long productId, ProductRequest request) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new CustomException(ErrorContent.PRODUCT_NOT_EXIST)
        );

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(categoryRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new CustomException(ErrorContent.CATEGORY_NOT_EXIST)
        ));
        product.setBrand(brandRepository.findById(request.getBrandId()).orElseThrow(
                () -> new CustomException(ErrorContent.BRAND_NOT_EXIST)
        ));

        productRepository.save(product);
    }

    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public ProductVariantDto createVariant(Long productId, ProductVariantRequest request) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new CustomException(ErrorContent.PRODUCT_NOT_EXIST)
        );

        if (productVariantRepository.existsBySku(request.getSku()))
            throw new CustomException(ErrorContent.VARIANT_SKU_ALREADY_EXIST);

        ProductVariant newVariant = ProductVariant.builder()
                .product(product)
                .color(request.getColor())
                .size(request.getSize())
                .price(request.getPrice())
                .stock(request.getStock())
                .sku(request.getSku())
                .build();
        productVariantRepository.save(newVariant);

        return new ProductVariantDto(newVariant);
    }
}
