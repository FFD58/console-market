package ru.fafurin.exception;

public class UserWithTheEmailAlreadyRegisteredException extends RuntimeException {
    public UserWithTheEmailAlreadyRegisteredException(String email) {
        super(String.format("User with the email %s already registered", email));
    }
}
