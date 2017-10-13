package ru.arink_group.deliveryapp.presentation.presenter;

import ru.arink_group.deliveryapp.domain.Product;

/**
 * Created by kirillvs on 03.10.17.
 */

public interface ProductsPresenter extends Presenter {

    void getProducts(int categoryId);
    int onProductSelect(boolean isAdd, Product product);
    void addItemsToCart();
}
