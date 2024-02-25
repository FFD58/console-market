package ru.fafurin.entity.cart;

import ru.fafurin.entity.product.ProductInterface;

public class CartItem implements CartItemInterface {

    private ProductInterface product;
    private int count;

    public CartItem(ProductInterface product, int count) {
        this.product = product;
        this.count = count;
    }

    public ProductInterface getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }

    public int getTotal() {
        return product.getPrice() * getCount();
    }

    @Override
    public String toString() {
        return "productId:" + product.getId() + " count:" + count + ";";
    }
}
