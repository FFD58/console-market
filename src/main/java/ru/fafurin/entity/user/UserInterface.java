package ru.fafurin.entity.user;

import ru.fafurin.entity.Entity;

public interface UserInterface extends Entity {

    int getId();
    String getEmail();

    String getPassword();

    String getName();
    String getPhone();
    String getAddress();

    void setName(String name);

    void setPhone(String phone);

    void setAddress(String address);
}
