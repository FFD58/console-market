package ru.fafurin.entity.order;

import ru.fafurin.entity.cart.CartItemInterface;
import ru.fafurin.entity.user.UserInterface;

import java.util.ArrayList;

public class Order implements OrderInterface {
    private final UserInterface user;
    private final ArrayList<CartItemInterface> cart;

    public Order(UserInterface user, ArrayList<CartItemInterface> cart) {
        this.user = user;
        this.cart = cart;
    }

    public UserInterface getUser() {
        return user;
    }

    public ArrayList<CartItemInterface> getCart() {
        return cart;
    }

    @Override
    public String toString() {
        String userInfo = String.format("User:\n%s\n%s\n%s\n\n", user.getName(), user.getPhone(), user.getAddress());
        return userInfo + getCartInfo();
    }

    private String getCartInfo() {
        int total = 0;
        StringBuilder result = new StringBuilder("Cart:\n");
        for (CartItemInterface cartItem : cart) {
            result.append(String.format("%s\nprice: %d\ncount: %s\n\n", cartItem.getProduct().getTitle(), cartItem.getProduct().getPrice(), cartItem.getCount()));
            total += cartItem.getTotal();
        }
        result.append(String.format("Total: %d\n\n", total));
        return result.toString();
    }
}
