package ru.fafurin.entity.order;

import ru.fafurin.entity.Entity;
import ru.fafurin.entity.cart.CartItemInterface;
import ru.fafurin.entity.user.UserInterface;

import java.util.ArrayList;


public interface OrderInterface extends Entity {
    UserInterface getUser();

    ArrayList<CartItemInterface> getCart();
}
