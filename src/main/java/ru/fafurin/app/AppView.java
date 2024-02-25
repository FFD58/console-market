package ru.fafurin.app;

import ru.fafurin.comparators.AppComparator;
import ru.fafurin.entity.product.ProductInterface;

import java.util.ArrayList;

public abstract class AppView {
    private String title;
    private ArrayList<AppView> children;

    private int currentPage = 0;

    private final int pageLimit = 5;

    private boolean hasNextPage = false;

    private boolean hasPreviousPage = false;

    public ArrayList<AppComparator<ProductInterface>> availableComparators = new ArrayList<>();

    public AppComparator<ProductInterface> selectedComparator;

    protected AppView(String title, ArrayList<AppView> children) {
        this.title = title;
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<AppView> getChildren() {
        return children;
    }

    public void action() {
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }
}
