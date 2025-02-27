package pro.vhbchieu.sStore.sys.service;

import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.sys.domain.dto.category.CategoryCreateDto;

@Service
public interface CategoryService {
    void create(CategoryCreateDto request);
}
