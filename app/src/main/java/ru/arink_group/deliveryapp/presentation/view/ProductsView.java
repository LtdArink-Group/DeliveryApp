package ru.arink_group.deliveryapp.presentation.view;

import java.util.List;

import ru.arink_group.deliveryapp.domain.Product;

/**
 * Created by kirillvs on 03.10.17.
 */

public interface ProductsView {

    void setProductsList(List<Product> products);

    void updateProductList(List<Product> selectedProducts);

    void showErrorMessage(String message);

}
