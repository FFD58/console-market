package ru.fafurin.repository.user;

import ru.fafurin.entity.user.User;
import ru.fafurin.entity.user.UserInterface;
import ru.fafurin.exception.UserWithTheEmailAlreadyRegisteredException;
import ru.fafurin.service.FileService;

import java.util.ArrayList;

public class UserFileRepository implements UserRepositoryInterface {

    private FileService fileManager;

    public final String FILENAME = "users.txt";
    public ArrayList<UserInterface> users;

    public UserFileRepository(FileService fileManager) {
        this.fileManager = fileManager;
        this.users = getUsers();
    }

    public void createUser(String name, String email, String password, String phone, String address) {
        int id;
        if (users.isEmpty()) id = 1;
        else id = users.get(users.size() - 1).getId() + 1;

        UserInterface currentUser = new User(id, name, email, password, phone, address);
        for (UserInterface user : users) {
            if (user.getEmail().equals(email)) throw new UserWithTheEmailAlreadyRegisteredException(email);
        }
        addUser(currentUser);
        User.currentUserId = currentUser.getId();
        saveUsersToFile();
    }

    public void addUser(UserInterface user) {
        this.users.add(user);
    }

    public UserInterface getByEmail(String email) {
        for (UserInterface user : users) {
            if (user.getEmail().equals(email)) return user;
        }
        return null;
    }

    public UserInterface getById(int id) {
        for (UserInterface user : users) {
            if (user.getId() == id) return user;
        }
        return null;
    }

    public ArrayList<UserInterface> getUsers() {
        ArrayList<UserInterface> users = new ArrayList<>();
        String content = this.fileManager.getStringFromFile(FILENAME);

        String[] strings = content.split(";");

        for (String str : strings) {
            if (str.contains("password")) {
                UserInterface user = this.getUserFromString(str);
                users.add(user);
            }
        }
        return users;
    }

    public UserInterface getUserFromString(String str) {
        int idIndex = str.indexOf("id:");
        int id = Integer.parseInt(str.substring(idIndex + "id:".length(), str.indexOf(" name")));

        int nameIndex = str.indexOf("name:");
        String name = str.substring(nameIndex + "name:".length(), str.indexOf(" email"));

        int emailIndex = str.indexOf("email:");
        String email = str.substring(emailIndex + "email:".length(), str.indexOf(" password"));

        int passwordIndex = str.indexOf("password:");
        String password = str.substring(passwordIndex + "password:".length(), str.indexOf(" phone"));

        int phoneIndex = str.indexOf("phone:");
        String phone = str.substring(phoneIndex + "phone:".length(), str.indexOf(" address"));

        int addressIndex = str.indexOf("address:");
        String address = str.substring(addressIndex + "address:".length());
        return new User(id, name, email, password, phone, address);
    }

    public void saveUsersToFile() {
        this.fileManager.writeStringToFile(String.valueOf(this.users), this.FILENAME);
    }
}
