package ru.fafurin.service;

import ru.fafurin.entity.cart.CartItemInterface;
import ru.fafurin.entity.product.ProductInterface;
import ru.fafurin.repository.cart.CartRepositoryInterface;
import ru.fafurin.repository.product.ProductRepositoryInterface;

import java.util.ArrayList;

public class CartService {

    private ProductRepositoryInterface productFileRepository;
    private CartRepositoryInterface cartFileRepository;

    private ArrayList<CartItemInterface> cart;

    public CartService(ProductRepositoryInterface productFileRepository, CartRepositoryInterface cartFileRepository) {
        this.productFileRepository = productFileRepository;
        this.cartFileRepository = cartFileRepository;
    }

    public boolean addToCart(int productId, int productCount) {
        ProductInterface product = this.productFileRepository.getById(productId);
        if (product != null) {
            cartFileRepository.addToCart(product, productCount);
            productFileRepository.changeProductCountById(productId, productCount);
            return true;
        }
        return false;
    }

    public ArrayList<CartItemInterface> getCart() {
        cart = cartFileRepository.getCartByUserId();
        return cart;
    }
}
