package ru.fafurin.entity.product;

import ru.fafurin.entity.Entity;

public interface ProductInterface extends Entity {

    int getId();
    void setId(int id);
    String getTitle();
    int getPrice();
    int getCount();
    void setCount(int count);

    boolean isAvailable();

}
