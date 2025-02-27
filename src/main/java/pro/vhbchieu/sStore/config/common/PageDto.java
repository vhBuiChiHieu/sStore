package pro.vhbchieu.sStore.config.common;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDto<T> {
    private Integer pageIndex;
    private Integer pageSize;
    private int totalPages;
    private int totalItems;
    private int beginIndex;
    private int endIndex;
    private List<T> data;

    public static <T> PageDto<T> of(Page<T> page, List<T> data) {
        PageDto<T> pageDto = new PageDto<>();
        pageDto.pageIndex = page.getPageable().getPageNumber() + 1;
        pageDto.pageSize = page.getPageable().getPageSize();
        pageDto.totalPages = page.getTotalPages();
        pageDto.totalItems = Math.toIntExact(page.getTotalElements());
        pageDto.beginIndex = Math.toIntExact(page.getPageable().getOffset());
        pageDto.endIndex = Math.toIntExact(page.getPageable().getOffset() + page.getNumberOfElements());
        pageDto.data = data;
        return pageDto;
    }

    public static <T> PageDto<T> of(Page<T> page) {
        return PageDto.<T>builder()
                .pageIndex(page.getPageable().getPageNumber() + 1)
                .pageSize(page.getPageable().getPageSize())
                .totalPages(page.getTotalPages())
                .totalItems(Math.toIntExact(page.getTotalElements()))
                .beginIndex(Math.toIntExact(page.getPageable().getOffset()))
                .endIndex(Math.toIntExact(page.getPageable().getOffset() + page.getNumberOfElements()))
                .data(page.getContent())
                .build();
    }

    public static <T> PageDto<T> empty() {
        return PageDto.<T>builder()
                .pageIndex(0)
                .pageSize(0)
                .totalPages(0)
                .totalItems(0)
                .beginIndex(0)
                .endIndex(0)
                .data(List.of())
                .build();
    }
}
