package ru.fafurin.repository.order;

import ru.fafurin.entity.cart.CartItemInterface;
import ru.fafurin.entity.order.Order;
import ru.fafurin.entity.order.OrderInterface;
import ru.fafurin.entity.user.User;
import ru.fafurin.entity.user.UserInterface;
import ru.fafurin.exception.CartIsEmptyException;
import ru.fafurin.exception.UserNotFoundException;
import ru.fafurin.repository.cart.CartRepositoryInterface;
import ru.fafurin.repository.user.UserRepositoryInterface;
import ru.fafurin.service.FileService;

import java.util.ArrayList;

public class OrderFileRepository implements OrderRepositoryInterface {
    private final FileService fileManager;
    private final UserRepositoryInterface userFileRepository;
    private final CartRepositoryInterface cartFileRepository;
    public final String PATH = "orders/";
    public OrderInterface order;

    public OrderFileRepository(
            FileService fileManager,
            UserRepositoryInterface userFileRepository,
            CartRepositoryInterface cartFileRepository) {
        this.fileManager = fileManager;
        this.userFileRepository = userFileRepository;
        this.cartFileRepository = cartFileRepository;
    }

    public void createOrder(int currentUserId) {
        this.order = getOrderByUserId(currentUserId);
        saveOrderToFile();
    }

    public OrderInterface getOrderByUserId(int currentUserId) {
        UserInterface currentUser = userFileRepository.getById(currentUserId);
        ArrayList<CartItemInterface> cart = cartFileRepository.getCartByUserId();
        if (currentUser == null) throw new UserNotFoundException(currentUserId);
        if (cart == null) throw new CartIsEmptyException();
        return new Order(currentUser, cart);
    }

    private void saveOrderToFile() {
        String filename = PATH + User.currentUserId;
        this.fileManager.writeStringToFile(String.valueOf(order), filename);
    }
}
