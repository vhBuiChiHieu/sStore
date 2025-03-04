package pro.vhbchieu.sStore.sys.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.category.CategoryCreateDto;
import pro.vhbchieu.sStore.sys.domain.dto.category.CategoryDto;
import pro.vhbchieu.sStore.sys.domain.entity.product.Category;
import pro.vhbchieu.sStore.sys.repository.CategoryRepository;
import pro.vhbchieu.sStore.sys.service.CategoryService;
import pro.vhbchieu.sStore.sys.utils.StringUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void create(CategoryCreateDto request) {
        String slug = StringUtils.toSlug(request.getName());
        if (categoryRepository.existsBySlug(slug))
            throw new CustomException(ErrorContent.SLUG_ALREADY_EXIST);

        Category newCategory  = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .slug(slug)
                .build();

        categoryRepository.save(newCategory);
    }

    @Override
    public List<CategoryDto> getList() {
        return categoryRepository.findAll().stream().map(CategoryDto::new).toList();
    }

    @Override
    public void update(Long categoryId, CategoryCreateDto request) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CustomException(ErrorContent.CATEGORY_NOT_EXIST)
        );

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setSlug(StringUtils.toSlug(request.getName()));

        categoryRepository.save(category);
    }

    @Override
    public void delete(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public PageDto<CategoryDto> getPage(Integer pageIndex, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);

        Page<CategoryDto> page = categoryRepository.findAll(pageable).map(CategoryDto::new);

        return PageDto.of(page);
    }
}
