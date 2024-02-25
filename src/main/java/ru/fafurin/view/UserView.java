package ru.fafurin.view;

import ru.fafurin.app.AppView;
import ru.fafurin.exception.UserNotFoundException;
import ru.fafurin.service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class UserView extends AppView {
    private Scanner scanner = new Scanner(System.in);

    private UserService userService;

    public UserView(UserService userService, ArrayList<AppView> children) {
        super("Login/Registration", children);
        this.userService = userService;
    }

    @Override
    public void action() {
        System.out.println("Login\n");
        System.out.println("Please, enter email:");
        String email = userService.getValidator().validateEmail(scanner);
        System.out.println("Please, enter password:");
        String password = userService.getValidator().validatePassword(scanner);
        try {
            userService.logIn(email, password);
            System.out.println("You are successfully logged in\n");
        } catch (UserNotFoundException e) {
            registerUser();
        }
    }

    private void registerUser() {
        System.out.println("You are not registered");
        System.out.println("Please, enter your name...");
        String name = scanner.next();
        System.out.println("Please, enter a phone number...");
        String phone = userService.getValidator().validatePhone(scanner);
        System.out.println("Please, enter address to delivery...");
        String address = scanner.next();
        System.out.println("Please, enter your email to login this application next time...");
        String email = userService.getValidator().validateEmail(scanner);
        System.out.println("What would be your password?");
        String password = userService.getValidator().validatePassword(scanner);
        if (name != null && phone != null && address != null) {
            userService.register(name, email, password, phone, address);
            System.out.println("You are successfully registered\n");
        }
    }
}
