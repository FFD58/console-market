package ru.fafurin;

import ru.fafurin.app.AppView;
import ru.fafurin.app.PageLoop;
import ru.fafurin.comparators.AppComparator;
import ru.fafurin.comparators.PriceComparator;
import ru.fafurin.entity.product.ProductInterface;
import ru.fafurin.repository.cart.CartFileRepository;
import ru.fafurin.repository.cart.CartRepositoryInterface;
import ru.fafurin.repository.order.OrderFileRepository;
import ru.fafurin.repository.order.OrderRepositoryInterface;
import ru.fafurin.repository.product.ProductFileRepository;
import ru.fafurin.repository.product.ProductRepositoryInterface;
import ru.fafurin.repository.user.UserFileRepository;
import ru.fafurin.repository.user.UserRepositoryInterface;
import ru.fafurin.service.*;
import ru.fafurin.view.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FileService fileManager = new FileService();

        ProductRepositoryInterface productRepository = new ProductFileRepository(fileManager);
        UserRepositoryInterface userFileRepository = new UserFileRepository(fileManager);
        CartRepositoryInterface cartRepository = new CartFileRepository(fileManager, productRepository);
        OrderRepositoryInterface orderRepository = new OrderFileRepository(fileManager, userFileRepository, cartRepository);

        UserService userService = new UserService(userFileRepository);
        OrderService orderService = new OrderService(orderRepository, cartRepository, productRepository);
        CatalogService catalogService = new CatalogService(productRepository);

        ArrayList<AppComparator<ProductInterface>> comparators = new ArrayList<>();
        comparators.add(new AppComparator<>(new PriceComparator(false), "Sort by descending price"));
        comparators.add(new AppComparator<>(new PriceComparator(), "Sort by ascending price"));


        AppView addToCartView = new AddToCartView(new CartService(productRepository, cartRepository), new ArrayList<>());

        ArrayList<AppView> catalogChildren = new ArrayList<>();
        catalogChildren.add(addToCartView);



        AppView catalogView = new CatalogView(catalogService, catalogChildren, comparators);

        AppView orderView = new OrderView(orderService, new ArrayList<>());
        AppView clearCartView = new ClearCartView(orderService, new ArrayList<>());

        ArrayList<AppView> cartChildren = new ArrayList<>();
        cartChildren.add(orderView);
        cartChildren.add(clearCartView);

        AppView cartView = new CartView(new CartService(productRepository, cartRepository), cartChildren);
        ArrayList<AppView> mainChildren = new ArrayList<>();
        mainChildren.add(catalogView);
        mainChildren.add(cartView);
        mainChildren.add(orderView);

        MainView mainView = new MainView(mainChildren);
        UserView userView = new UserView(userService, new ArrayList<>());
        userView.action();
        new PageLoop(mainView).run();
    }
}
