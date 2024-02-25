package ru.fafurin.view;

import ru.fafurin.app.AppView;
import ru.fafurin.service.CartService;

import java.util.ArrayList;
import java.util.Scanner;

public class AddToCartView extends AppView {

    private final CartService cartService;

    public AddToCartView(CartService cartService, ArrayList<AppView> children) {
        super("Add products to cart", children);
        this.cartService = cartService;
    }

    @Override
    public void action() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter a product id...");
        int productId = scanner.nextInt();
        System.out.println("Please, enter a product count...");
        int productCount = scanner.nextInt();
        boolean res = cartService.addToCart(productId, productCount);
        if (res) System.out.println("The product added to the cart");
        else System.out.println("Failed to add the product to the cart");
    }
}
