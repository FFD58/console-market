package ru.fafurin.service;

import ru.fafurin.entity.order.OrderInterface;
import ru.fafurin.exception.CartIsEmptyException;
import ru.fafurin.repository.cart.CartRepositoryInterface;
import ru.fafurin.repository.order.OrderRepositoryInterface;
import ru.fafurin.repository.product.ProductRepositoryInterface;

public class OrderService {

    private final OrderRepositoryInterface orderFileRepository;
    private final CartRepositoryInterface cartFileRepository;
    private final ProductRepositoryInterface productFileRepository;

    public OrderService(
            OrderRepositoryInterface orderFileRepository,
            CartRepositoryInterface cartFileRepository,
            ProductRepositoryInterface productFileRepository) {
        this.orderFileRepository = orderFileRepository;
        this.cartFileRepository = cartFileRepository;
        this.productFileRepository = productFileRepository;
    }

    public OrderInterface getOrderInfo(int currentUserId) {
        OrderInterface order = null;
        try {
            order = orderFileRepository.getOrderByUserId(currentUserId);
        } catch (CartIsEmptyException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    public void clearOrder() {
        cartFileRepository.clearCart();
    }

    public void createOrder(int currentUserId) {
        orderFileRepository.createOrder(currentUserId);
        productFileRepository.saveProductCatalogToFile();
    }
}
