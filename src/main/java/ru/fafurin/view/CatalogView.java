package ru.fafurin.view;

import ru.fafurin.app.AppView;
import ru.fafurin.comparators.AppComparator;
import ru.fafurin.entity.product.ProductInterface;
import ru.fafurin.service.CatalogService;

import java.util.ArrayList;

public class CatalogView extends AppView {

    private final CatalogService catalogService;

    public CatalogView(CatalogService catalogService, ArrayList<AppView> children, ArrayList<AppComparator<ProductInterface>> comparators) {
        super("Catalog", children);
        this.catalogService = catalogService;
        availableComparators.addAll(comparators);
        if (!availableComparators.isEmpty()) {
            selectedComparator = availableComparators.get(0);
        }
    }

    @Override
    public void action() {
        int availableProductsCount = catalogService.getAvailableProductsCount();
        int pageCount = availableProductsCount / getPageLimit();

        if (availableProductsCount < getPageLimit()) {
            setHasNextPage(false);
            setHasPreviousPage(false);
        } else {
            if (getCurrentPage() == 0) {
                setHasNextPage(true);
                setHasPreviousPage(false);
            } else if (getCurrentPage() == pageCount) {
                setHasNextPage(false);
                setHasPreviousPage(true);
            }
        }
        ArrayList<ProductInterface> catalog = catalogService.getCatalog(getCurrentPage(), getPageLimit(), selectedComparator.comparator);
        System.out.println("We can offer the following products:");
        for (ProductInterface product : catalog) {
            System.out.printf("id: %d - %s\nprice: %d count: %d\n\n", product.getId(), product.getTitle(), product.getPrice(), product.getCount());
        }
    }
}
