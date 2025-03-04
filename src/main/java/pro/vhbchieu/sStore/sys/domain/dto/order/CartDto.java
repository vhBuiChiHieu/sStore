package pro.vhbchieu.sStore.sys.domain.dto.order;

import lombok.Getter;
import lombok.Setter;
import pro.vhbchieu.sStore.sys.domain.dto.account.AccountDetailDto;
import pro.vhbchieu.sStore.sys.domain.dto.account.AccountDto;
import pro.vhbchieu.sStore.sys.domain.entity.order.Cart;

import java.util.List;

@Setter
@Getter
public class CartDto {
    protected Long id;
    protected AccountDto account;
    protected List<CartItemDto> cartItems;

    public CartDto(Cart cart) {
        this.id = cart.getId();
        this.account = new AccountDetailDto(cart.getAccount(), "simple");
        this.cartItems = cart.getCartItems().stream()
                .map(CartItemDto::new)
                .toList();
    }
}
