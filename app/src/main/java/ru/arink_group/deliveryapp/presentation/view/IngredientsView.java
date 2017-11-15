package ru.arink_group.deliveryapp.presentation.view;

import ru.arink_group.deliveryapp.domain.dao.Product;

/**
 * Created by kirillvs on 17.10.17.
 */

public interface IngredientsView {
    void setIngredientsList(Product product);
    void updateIngredientsList(Product product);
    void showErrorMessage(String message);
}
