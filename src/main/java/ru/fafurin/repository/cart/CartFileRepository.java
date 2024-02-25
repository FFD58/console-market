package ru.fafurin.repository.cart;

import ru.fafurin.entity.cart.CartItem;
import ru.fafurin.entity.cart.CartItemInterface;
import ru.fafurin.entity.product.ProductInterface;
import ru.fafurin.entity.user.User;
import ru.fafurin.repository.product.ProductRepositoryInterface;
import ru.fafurin.service.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CartFileRepository implements CartRepositoryInterface {

    private final ProductRepositoryInterface productFileRepository;

    private ArrayList<CartItemInterface> cart = new ArrayList<>();

    public final String PATH = "carts/";

    private final FileService fileManager;

    public CartFileRepository(FileService fileManager, ProductRepositoryInterface productFileRepository) {
        this.fileManager = fileManager;
        this.productFileRepository = productFileRepository;
    }

    public ArrayList<CartItemInterface> getCartByUserId() {
        String filename = PATH + User.currentUserId;
        if (checkFileIsEmpty(filename)) return getCartFromFile(filename);
        else return null;
    }

    @Override
    public void addToCart(ProductInterface product, int count) {
        cart.add(new CartItem(product, count));
        saveCartToFile();
    }

    public ArrayList<CartItemInterface> getCartFromFile(String filename) {
        ArrayList<CartItemInterface> cart = new ArrayList<>();

        String content = this.fileManager.getStringFromFile(filename);
        String[] strings = content.split(";");
        for (String str : strings) {
            if (str.contains("productId")) {
                ProductInterface product = productFileRepository.getById(getProductIdFromString(str));
                int count = getProductCountFromString(str);
                cart.add(new CartItem(product, count));
            }
        }
        this.cart = cart;
        return cart;
    }

    @Override
    public void clearCart() {
        cart.clear();
        Path path = Paths.get(PATH + User.currentUserId);
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int getProductIdFromString(String str) {
        int idIndex = str.indexOf("productId:");
        return Integer.parseInt(str.substring(idIndex + "productId:".length(), str.indexOf(" count")));
    }

    private int getProductCountFromString(String str) {
        int countIndex = str.indexOf("count:");
        return Integer.parseInt(str.substring(countIndex + "count:".length()));
    }

    private void saveCartToFile() {
        String filename = PATH + User.currentUserId;
        this.fileManager.writeStringToFile(String.valueOf(cart), filename);
    }

    private boolean checkFileIsEmpty(String filename) {
        File file = new File(filename);
        return file.getTotalSpace() > 0;
    }
}
