package pro.vhbchieu.sStore.sys.service;

import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.sys.domain.dto.order.CartDto;

@Service
public interface CartService {
    CartDto getDetail(Long cartId);

    PageDto<CartDto> getPage(@Min(1) Integer pageIndex, @Min(1) Integer pageSize);
}
