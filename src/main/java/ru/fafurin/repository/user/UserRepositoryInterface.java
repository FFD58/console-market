package ru.fafurin.repository.user;

import ru.fafurin.entity.user.UserInterface;
import ru.fafurin.repository.Repository;

import java.util.ArrayList;

public interface UserRepositoryInterface extends Repository {
    ArrayList<UserInterface> getUsers();

    UserInterface getByEmail(String email);

    UserInterface getById(int id);

    void addUser(UserInterface user);

    void saveUsersToFile();

    void createUser(String name, String email, String password, String phone, String address);
}
