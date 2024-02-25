package ru.fafurin.validator;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleValidator {

    public String validateEmail(Scanner scanner) {
        String email;
        while (true) {
            email = scanner.next();
            if (checkEmail(email)) {
                break;
            } else System.out.println("Wrong email format");
        }
        return email;
    }

    public String validatePassword(Scanner scanner) {
        String password;
        while (true) {
            password = scanner.next();
            if (checkPassword(password)) break;
            else System.out.println("Wrong password format.\n" +
                    "A digit must occur at least once\n" +
                    "A lower case letter must occur at least once\n" +
                    "An upper case letter must occur at least once\n" +
                    "4-12 character password, both inclusive");
        }
        return password;
    }

    public String validatePhone(Scanner scanner) {
        String phone;
        while (true) {
            phone = scanner.next();
            if (checkPhone(phone)) {
                break;
            } else System.out.println("Wrong phone format\n " +
                    "The phone must contain 11 digits");
        }
        return phone;
    }

    private boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        return pattern.matcher(email).matches();
    }

    private boolean checkPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,12}$");
        return pattern.matcher(password).matches();
    }

    private boolean checkPhone(String phone) {
        Pattern pattern = Pattern.compile("^8[0-9]{10}$");
        return pattern.matcher(phone).matches();
    }

    public static void main(String[] args) {
        ConsoleValidator validator = new ConsoleValidator();
        Scanner scanner = new Scanner(System.in);
        validator.validatePhone(scanner);
    }
}
