package ru.fafurin.repository.order;

import ru.fafurin.entity.order.OrderInterface;
import ru.fafurin.repository.Repository;

public interface OrderRepositoryInterface extends Repository {
    void createOrder(int currentUserId);
    OrderInterface getOrderByUserId(int currentUserId);
}
