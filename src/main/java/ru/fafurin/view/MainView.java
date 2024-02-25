package ru.fafurin.view;

import ru.fafurin.app.AppView;

import java.util.ArrayList;

public class MainView extends AppView {

    public MainView(ArrayList<AppView> children) {
        super("Welcome to Bakery-online!", children);
    }
}
