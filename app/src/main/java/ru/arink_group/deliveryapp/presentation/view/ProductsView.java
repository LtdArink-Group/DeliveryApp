package ru.arink_group.deliveryapp.presentation.view;

import java.util.List;

import ru.arink_group.deliveryapp.domain.dao.Product;

/**
 * Created by kirillvs on 03.10.17.
 */

public interface ProductsView extends ProgressView{

    void setProductsList(List<Product> products);

    void updateProductList(List<Product> selectedProducts);

    void showErrorMessage(String message);

    void showPlaceholder();

}
