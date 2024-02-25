package ru.fafurin.service;

import ru.fafurin.entity.user.User;
import ru.fafurin.entity.user.UserInterface;
import ru.fafurin.exception.InvalidInputDataException;
import ru.fafurin.exception.UserNotFoundException;
import ru.fafurin.exception.UserWithTheEmailAlreadyRegisteredException;
import ru.fafurin.repository.user.UserRepositoryInterface;
import ru.fafurin.validator.ConsoleValidator;

public class UserService {
    UserRepositoryInterface userRepository;
    ConsoleValidator validator = new ConsoleValidator();

    public UserService(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    public ConsoleValidator getValidator() {
        return validator;
    }

    public UserInterface logIn(String email, String password) {
        if (email != null && password != null) {
            for (UserInterface user : userRepository.getUsers()) {
                if (user.getEmail().equals(email)) {
                    if (user.getPassword().equals(password)) {
                        User.currentUserId = user.getId();
                        return user;
                    }
                }
            }
            throw new UserNotFoundException(email);
        }
        throw new InvalidInputDataException();
    }

    public void register(String name, String email, String password, String phone, String address) {
        try {
            userRepository.createUser(name, email, password, phone, address);
        } catch (UserWithTheEmailAlreadyRegisteredException e) {
            throw new UserWithTheEmailAlreadyRegisteredException(email);
        }
    }
}

