package pro.vhbchieu.sStore.sys.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.sys.domain.dto.category.CategoryCreateDto;
import pro.vhbchieu.sStore.sys.domain.dto.category.CategoryDto;

import java.util.List;

@Service
public interface CategoryService {
    void create(CategoryCreateDto request);

    List<CategoryDto> getList();

    void update(Long categoryId, @Valid CategoryCreateDto request);

    void delete(Long categoryId);

    PageDto<CategoryDto> getPage(@Min(1) Integer pageIndex, @Min(1) Integer pageSize);
}
