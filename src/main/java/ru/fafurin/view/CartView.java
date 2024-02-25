package ru.fafurin.view;

import ru.fafurin.app.AppView;
import ru.fafurin.entity.cart.CartItemInterface;
import ru.fafurin.service.CartService;

import java.util.ArrayList;

public class CartView extends AppView {

    private final CartService cartService;

    public CartView(CartService cartService, ArrayList<AppView> children) {
        super("Cart", children);
        this.cartService = cartService;
    }

    @Override
    public void action() {
        ArrayList<CartItemInterface> cart = cartService.getCart();
        if (cart != null) {
            System.out.println("Shopping cart contents:");
            int total = 0;
            for (CartItemInterface cartItem : cart) {
                System.out.printf("%s\nprice: %d\ncount: %s\n\n", cartItem.getProduct().getTitle(), cartItem.getProduct().getPrice(), cartItem.getCount());
                total += cartItem.getTotal();
            }
            System.out.printf("Total: %d\n\n", total);
        } else System.out.println("Cart is empty\n");
    }
}
