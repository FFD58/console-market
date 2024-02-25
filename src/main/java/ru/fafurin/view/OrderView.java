package ru.fafurin.view;

import ru.fafurin.app.AppView;
import ru.fafurin.entity.order.OrderInterface;
import ru.fafurin.entity.user.User;
import ru.fafurin.service.OrderService;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderView extends AppView {

    private final OrderService orderService;

    public OrderView(OrderService orderService, ArrayList<AppView> children) {
        super("Place an order", children);
        this.orderService = orderService;
    }

    @Override
    public void action() {
        OrderInterface order = orderService.getOrderInfo(User.currentUserId);
        if (order != null) {
            System.out.println(order);
            System.out.println("Place an order?");
            System.out.println("Please, enter yes or no...");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.next();
            if (answer.equals("yes")) {
                orderService.createOrder(User.currentUserId);
                System.out.println("Your order has been successfully placed");
                orderService.clearOrder();
            }
        }
    }
}
