package ru.fafurin.view;

import ru.fafurin.app.AppView;
import ru.fafurin.service.OrderService;

import java.util.ArrayList;

public class ClearCartView extends AppView {

    private final OrderService orderService;

    public ClearCartView(OrderService orderService, ArrayList<AppView> children) {
        super("Clear cart", children);
        this.orderService = orderService;
    }

    @Override
    public void action() {
        orderService.clearOrder();
    }
}
