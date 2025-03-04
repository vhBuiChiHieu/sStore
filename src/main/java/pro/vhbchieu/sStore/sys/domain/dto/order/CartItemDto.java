package pro.vhbchieu.sStore.sys.domain.dto.order;

import lombok.Getter;
import lombok.Setter;
import pro.vhbchieu.sStore.sys.domain.dto.product.ProductVariantDto;
import pro.vhbchieu.sStore.sys.domain.entity.order.CartItem;

@Getter
@Setter
public class CartItemDto {
    protected Long id;
    protected Integer quantity;
    protected ProductVariantDto productVariant;

    public CartItemDto(CartItem cartItem) {
        this.id = cartItem.getId();
        this.quantity = cartItem.getQuantity();
        this.productVariant = new ProductVariantDto(cartItem.getProductVariant());
    }
}
