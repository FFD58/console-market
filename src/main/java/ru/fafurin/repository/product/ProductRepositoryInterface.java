package ru.fafurin.repository.product;

import ru.fafurin.entity.product.ProductInterface;
import ru.fafurin.repository.Repository;

import java.util.ArrayList;
import java.util.Comparator;

public interface ProductRepositoryInterface extends Repository {
    public ArrayList<ProductInterface> getCatalog(int page, int limit, Comparator<ProductInterface> comparator);
    ProductInterface getById(int id);
    void saveProductCatalogToFile();

    void addProductToCatalog(ProductInterface product);


    ArrayList<ProductInterface> getProductsFromFile();

    int getAvailableProductsCount();
    void changeProductCountById(int productId, int count);
}
