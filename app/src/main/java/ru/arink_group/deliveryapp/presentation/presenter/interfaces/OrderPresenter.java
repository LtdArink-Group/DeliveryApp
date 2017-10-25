package ru.arink_group.deliveryapp.presentation.presenter.interfaces;

import ru.arink_group.deliveryapp.domain.Product;

/**
 * Created by kirillvs on 24.10.17.
 */

public interface OrderPresenter extends Presenter {
    void getProductsFromBasket();
    void getTotals();
    void updateProduct(Product product);
}
