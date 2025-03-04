package pro.vhbchieu.sStore.sys.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.sys.domain.dto.order.CartDto;
import pro.vhbchieu.sStore.sys.service.CartService;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/page")
    public PageDto<CartDto> getPage(
            @Min(1) @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @Min(1) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return cartService.getPage(pageIndex, pageSize);
    }

    @GetMapping("/{cartId}")
    public CartDto getDetail(@PathVariable("cartId") Long cartId) {
        return cartService.getDetail(cartId);
    }
}
