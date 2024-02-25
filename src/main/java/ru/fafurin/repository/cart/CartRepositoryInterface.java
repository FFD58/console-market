package ru.fafurin.repository.cart;

import ru.fafurin.entity.cart.CartItemInterface;
import ru.fafurin.entity.product.ProductInterface;
import ru.fafurin.repository.Repository;

import java.util.ArrayList;

public interface CartRepositoryInterface extends Repository {
    void addToCart(ProductInterface product, int count);
    ArrayList<CartItemInterface> getCartByUserId();
    void clearCart();
}
