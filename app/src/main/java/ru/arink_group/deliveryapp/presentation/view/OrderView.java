package ru.arink_group.deliveryapp.presentation.view;

import java.util.List;

import ru.arink_group.deliveryapp.domain.Product;

/**
 * Created by kirillvs on 24.10.17.
 */

public interface OrderView {
    void setProducts(List<Product> products);
    void updateProductState(Product product);
    void updateTotals();
    void showErrorMessage(String e);
}
