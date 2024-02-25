package ru.fafurin.exception;

public class InvalidInputDataException extends RuntimeException {
    public InvalidInputDataException() {
        super("Invalid input data");
   }
}
