package ru.arink_group.deliveryapp.presentation.presenter.interfaces;

import ru.arink_group.deliveryapp.domain.dao.Ingredient;
import ru.arink_group.deliveryapp.domain.dao.Product;

/**
 * Created by kirillvs on 17.10.17.
 */

public interface IngredientsPresenter extends Presenter {
    void fetchProduct(int productId);
    void updateProduct(Product product);
    void addIngredientToBasket(int productId, Ingredient ingredient);
}
