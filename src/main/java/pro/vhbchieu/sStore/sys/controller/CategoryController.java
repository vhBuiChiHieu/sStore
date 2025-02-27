package pro.vhbchieu.sStore.sys.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.vhbchieu.sStore.sys.domain.dto.category.CategoryCreateDto;
import pro.vhbchieu.sStore.sys.service.CategoryService;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    public void create(@Valid @RequestBody CategoryCreateDto request) {
        categoryService.create(request);
    }
}
