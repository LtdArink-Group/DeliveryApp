package ru.arink_group.deliveryapp.presentation.view;

import java.util.List;

import ru.arink_group.deliveryapp.domain.dao.Category;
import ru.arink_group.deliveryapp.domain.dao.Product;

/**
 * Created by kirillvs on 02.10.17.
 */

public interface CategoriesView {
    void startCategory(int sectionId, String name);
    void setCategoriesList(List<Category> categories);
    void showErrorMessage(String message);
    void loadCompleted();
    void calculateAndUpdateTotals(List<Product> products);
}
