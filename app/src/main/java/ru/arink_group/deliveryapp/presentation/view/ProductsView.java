package ru.arink_group.deliveryapp.presentation.view;

import java.util.List;

import ru.arink_group.deliveryapp.domain.Product;
import ru.arink_group.deliveryapp.domain.SelectedProduct;

/**
 * Created by kirillvs on 03.10.17.
 */

public interface ProductsView {

    void startProduct(int productId);

    void setProductsList(List<Product> products);

    void updateProductList(List<SelectedProduct> selectedProducts);

    void showErrorMessage(String message);

}
