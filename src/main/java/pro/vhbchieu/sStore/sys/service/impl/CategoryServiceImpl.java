package pro.vhbchieu.sStore.sys.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.category.CategoryCreateDto;
import pro.vhbchieu.sStore.sys.domain.entity.product.Category;
import pro.vhbchieu.sStore.sys.repository.CategoryRepository;
import pro.vhbchieu.sStore.sys.service.CategoryService;
import pro.vhbchieu.sStore.sys.utils.StringUtils;

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
}
