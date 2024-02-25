package ru.fafurin.repository.product;

import ru.fafurin.entity.product.Product;
import ru.fafurin.entity.product.ProductInterface;
import ru.fafurin.service.FileService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

public class ProductFileRepository implements ProductRepositoryInterface {
    private ArrayList<ProductInterface> products = new ArrayList<>();
    public final String FILENAME = "catalog.txt";
    private FileService fileManager;

    public ProductFileRepository(FileService fileManager) {
        this.fileManager = fileManager;
        setProductsFromFile();
    }

    public ArrayList<ProductInterface> getProducts() {
        return products;
    }

    public ArrayList<ProductInterface> getCatalog() {
        ArrayList<ProductInterface> products = getProducts();
        return new ArrayList<>(products.stream().filter(ProductInterface::isAvailable).toList());
    }

    public ArrayList<ProductInterface> getCatalog(int page, int limit, Comparator<ProductInterface> comparator) {
        ArrayList<ProductInterface> products = getProducts();
        Stream<ProductInterface> productStream = products.stream()
                        .filter(ProductInterface::isAvailable)
                        .sorted(comparator)
                        .skip((long) page * limit)
                        .limit(limit);
        return new ArrayList<>(productStream.toList());
    }

    public void changeProductCountById(int productId, int count) {
        ProductInterface product = getById(productId);
        product.setCount(product.getCount() - count);
    }

    @Override
    public int getAvailableProductsCount() {
        return getCatalog().size();
    }

    public ProductInterface getById(int id) {
        ArrayList<ProductInterface> products = getCatalog();
        for (ProductInterface product : products) {
            if (product.getId() == id) return product;
        }
        return null;
    }

    private void setProductsFromFile() {
        this.products = getProductsFromFile();
    }

    public ArrayList<ProductInterface> getProductsFromFile() {
        ArrayList<ProductInterface> products = new ArrayList<>();
        String content = this.fileManager.getStringFromFile(FILENAME);

        String[] strings = content.split(";");

        for (String str : strings) {
            if (str.contains("price")) {
                ProductInterface product = this.getProductFromString(str);
                products.add(product);
            }
        }
        return products;
    }

    public ProductInterface getProductFromString(String str) {
        int idIndex = str.indexOf("id:");
        String id = str.substring(idIndex + "id:".length(), str.indexOf("title", idIndex) - 1);

        int titleIndex = str.indexOf("title:");
        String title = str.substring(titleIndex + "title:".length(), str.indexOf("price", titleIndex) - 1);

        int priceIndex = str.indexOf("price:");
        String price = str.substring(priceIndex + "price:".length(), str.indexOf("count", priceIndex) - 1);

        int countIndex = str.indexOf("count:");
        String count = str.substring(countIndex + "count:".length());

        return new Product(Integer.parseInt(id), title, Integer.parseInt(price), Integer.parseInt(count), true);
    }

    public void addProductToCatalog(ProductInterface product) {
        this.products.add(product);
    }

    public void saveProductCatalogToFile() {
        this.fileManager.writeStringToFile(String.valueOf(this.products), this.FILENAME);
    }

}
