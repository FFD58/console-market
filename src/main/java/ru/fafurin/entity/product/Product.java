package ru.fafurin.entity.product;

public class Product implements ProductInterface {

    private int id;
    private String title;
    private int price;
    private int count;
    private boolean isAvailable;

    public Product(int id, String title, int price, int count, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.count = count;
        this.isAvailable = isAvailable;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return String.format("id:%d title:%s price:%d count:%d;\n", this.id, this.title, this.price, this.count);
    }
}
