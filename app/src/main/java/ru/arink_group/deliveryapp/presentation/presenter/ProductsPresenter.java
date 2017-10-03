package ru.arink_group.deliveryapp.presentation.presenter;

/**
 * Created by kirillvs on 03.10.17.
 */

public interface ProductsPresenter extends Presenter {

    void getProducts(int categoryId);
    void onProductSelect(int productId);

}
