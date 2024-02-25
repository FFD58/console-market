package ru.fafurin.service;

import ru.fafurin.entity.product.ProductInterface;
import ru.fafurin.repository.product.ProductRepositoryInterface;

import java.util.ArrayList;
import java.util.Comparator;

public class CatalogService {

    private final ProductRepositoryInterface productFileRepository;

    public CatalogService(ProductRepositoryInterface productFileRepository) {
        this.productFileRepository = productFileRepository;
    }

    public ArrayList<ProductInterface> getCatalog(int page, int limit, Comparator<ProductInterface> comparator) {
        return productFileRepository.getCatalog(page, limit, comparator);
    }

    public int getAvailableProductsCount() {
        return productFileRepository.getAvailableProductsCount();
    }
}
