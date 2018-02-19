package ru.arink_group.deliveryapp.presentation.presenter.interfaces;

import ru.arink_group.deliveryapp.domain.dao.Product;

/**
 * Created by kirillvs on 03.10.17.
 */

public interface ProductsPresenter extends Presenter {

    void getProducts(int categoryId);
    void updateProductsFromBasket();
    void addItemsListToBasket();
    void addItemToBasket(Product product);
}
