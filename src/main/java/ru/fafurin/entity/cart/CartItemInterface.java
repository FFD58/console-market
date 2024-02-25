package ru.fafurin.entity.cart;

import ru.fafurin.entity.Entity;
import ru.fafurin.entity.product.ProductInterface;

public interface CartItemInterface extends Entity {
    ProductInterface getProduct();
    int getCount();

    int getTotal();
}
