package ru.fafurin;

import ru.fafurin.entity.product.Product;
import ru.fafurin.entity.product.ProductInterface;
import ru.fafurin.repository.product.ProductFileRepository;
import ru.fafurin.repository.product.ProductRepositoryInterface;
import ru.fafurin.repository.user.UserFileRepository;
import ru.fafurin.service.FileService;

public class TestDataManager {
    public static void main(String[] args) {
        FileService fileManager = new FileService();
        ProductRepositoryInterface productRepository = new ProductFileRepository(fileManager);
        UserFileRepository userRepository = new UserFileRepository(fileManager);

        ProductInterface product1 = new Product(1,"Bread", 35, 45, true);
        ProductInterface product2 = new Product(2, "Hot-dog", 125, 50, true);
        ProductInterface product3 = new Product(3, "Bun", 43, 67, true);

        productRepository.addProductToCatalog(product1);
        productRepository.addProductToCatalog(product2);
        productRepository.addProductToCatalog(product3);

//        UserInterface user1 = new User("email@email.ru", "qwerty");
//        UserInterface user2 = new User( "another@email.ru", "1");
//
//        userRepository.addUser(user1);
//        userRepository.addUser(user2);

        System.out.println(userRepository.users);

        fileManager.writeStringToFile(userRepository.users.toString(), userRepository.FILENAME);

    }
}
