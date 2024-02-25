package ru.fafurin.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("User with email " + email + " not found.");
    }
    public UserNotFoundException(int id) {
        super("User with id " + id + " not found.");
    }

}
