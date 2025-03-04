package pro.vhbchieu.sStore.sys.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.sys.domain.dto.category.CategoryCreateDto;
import pro.vhbchieu.sStore.sys.domain.dto.category.CategoryDto;
import pro.vhbchieu.sStore.sys.service.CategoryService;

import java.util.List;

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

    @GetMapping()
    public List<CategoryDto> getList() {
        return categoryService.getList();
    }

    @GetMapping("/list")
    public PageDto<CategoryDto> getPage(
            @Min(1) @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @Min(1) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return categoryService.getPage(pageIndex, pageSize);
    }

    @PutMapping("/{categoryId}")
    public void update(@PathVariable Long categoryId, @Valid @RequestBody CategoryCreateDto request) {
        categoryService.update(categoryId, request);
    }

    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
    }
}
