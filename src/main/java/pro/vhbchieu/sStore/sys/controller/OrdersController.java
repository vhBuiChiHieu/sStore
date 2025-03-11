package pro.vhbchieu.sStore.sys.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.sys.domain.dto.order.*;
import pro.vhbchieu.sStore.sys.service.OrdersService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping("/page")
    public PageDto<OrdersDto> getPage(
            @Min(1) @RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
            @Min(1) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return ordersService.getPage(pageIndex, pageSize);
    }

    @GetMapping("/{orderId}")
    public OrdersDetailDto getDetail(@PathVariable("orderId") Long orderId) {
        return ordersService.getDetail(orderId);
    }

    @PostMapping()
    public void create(@RequestBody OrdersRequest request) {
        ordersService.create(request);
    }

    @PostMapping("/{orderId}/item")
    public void addItem(
            @PathVariable(name = "orderId") Long orderId,
            @RequestBody OrdersItemRequest request
    ) {
        ordersService.addItem(orderId, request);
    }

    @DeleteMapping("{orderId}")
    public void delete(
            @PathVariable(name = "orderId") Long orderId
    ) {
        ordersService.delete(orderId);
    }

    @PutMapping("/{orderId}")
    public void update(
            @PathVariable(name = "orderId") Long orderId,
            @RequestBody OrdersUpdateRequest request
    ) {
        ordersService.update(orderId, request);
    }

}
