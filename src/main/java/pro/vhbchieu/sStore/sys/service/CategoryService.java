package pro.vhbchieu.sStore.sys.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.sys.domain.dto.category.CategoryCreateDto;
import pro.vhbchieu.sStore.sys.domain.dto.category.CategoryDto;

import java.util.List;

@Service
public interface CategoryService {
    void create(CategoryCreateDto request);

    List<CategoryDto> getList();

    void update(Long categoryId, @Valid CategoryCreateDto request);

    void delete(Long categoryId);
}
