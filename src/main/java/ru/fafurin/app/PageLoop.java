package ru.fafurin.app;

import java.util.Scanner;

public class PageLoop {
    private AppView view;

    public PageLoop(AppView view) {
        this.view = view;
    }

    private int getChildrenSize() {
        return view.getChildren().size();
    }

    private int getOptionsSize() {
        int optionsSize = 0;
        if (view.isHasNextPage() || view.isHasPreviousPage()) optionsSize++;
        optionsSize += view.availableComparators.size();
        return optionsSize;
    }

    public void run() {
        while (true) {
            view.action();
            displayChildren();
            Scanner scanner = new Scanner(System.in);

            int value = scanner.nextInt();
            int fullSize = getChildrenSize() + getOptionsSize() + 1;

            if (value < 1 || value > fullSize) {
                System.out.println("Wrong page number");
            } else if (value == fullSize) {
                break;
            } else if (value <= getChildrenSize()) {
                AppView selectedView = view.getChildren().get(value - 1);
                new PageLoop(selectedView).run();
            } else {
                if (value == getChildrenSize() + 1 && (view.isHasNextPage() || view.isHasPreviousPage())) {
                    setPaginationCondition();
                } else {
                    setComparatorsCondition(value);
                }
                new PageLoop(view).run();
            }
        }
    }

    private void displayChildren() {
        int currentIndex = 1;
        int fullSize = getChildrenSize() + getOptionsSize() + 1;

        System.out.println(view.getTitle());
        System.out.printf("Enter a number (from 1 to %d)\n", fullSize);
        for (int i = 0; i < getChildrenSize(); i++) {
            AppView currentView = view.getChildren().get(i);
            System.out.println(currentIndex + " - " + currentView.getTitle());
            currentIndex++;
        }
        if (view.isHasNextPage() && !view.isHasPreviousPage()) {
            System.out.println(currentIndex + " - " + "Next page");
            currentIndex++;
        } else if (!view.isHasNextPage() && view.isHasPreviousPage()) {
            System.out.println(currentIndex + " - " + "Previous page");
            currentIndex++;
        }
        for (int i = 0; i < view.availableComparators.size(); i++) {
            System.out.println(currentIndex + " - " + view.availableComparators.get(i).name);
            currentIndex++;
        }
        System.out.println(currentIndex + " - Go back");
    }

    private void setComparatorsCondition(int value) {
        view.setCurrentPage(0);
        int comparatorIndex = value - getChildrenSize() - 1 - (view.isHasNextPage() ? 1 : 0) - (view.isHasPreviousPage() ? 1 : 0);
        view.selectedComparator = view.availableComparators.get(comparatorIndex);
    }

    private void setPaginationCondition() {
        if (view.isHasNextPage() && !view.isHasPreviousPage()) {
            view.setCurrentPage(view.getCurrentPage() + 1);
        } else if (!view.isHasNextPage() && view.isHasPreviousPage()) {
            view.setCurrentPage(view.getCurrentPage() - 1);
        }
    }
}
