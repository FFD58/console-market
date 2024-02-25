package ru.fafurin.comparators;

import ru.fafurin.entity.product.ProductInterface;

import java.util.Comparator;

public class PriceComparator implements Comparator<ProductInterface> {

    private boolean isAsc;

    public PriceComparator(boolean isAsc) {
        this.isAsc = isAsc;
    }

    public PriceComparator() {
        this.isAsc = true;
    }

    @Override
    public int compare(ProductInterface product1, ProductInterface product2) {
        if (isAsc) return product1.getPrice() - product2.getPrice();
        return product2.getPrice() - product1.getPrice();
    }
}
