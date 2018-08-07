package ru.arink_group.deliveryapp.presentation.view;

import java.util.List;

import ru.arink_group.deliveryapp.domain.dao.Address;
import ru.arink_group.deliveryapp.domain.dao.Product;
import ru.arink_group.deliveryapp.presentation.shared.DateTime;

/**
 * Created by kirillvs on 24.10.17.
 */

public interface OrderView extends ProgressView {
    void setProducts(List<Product> products);
    void updateProductState(Product product);
    void updateTotals();
    void updateAddresses(List<Address> addresses);
    void showCreateAccountButton();
    void showErrorMessage(String e);
    void showSendingOrderOk();
    void showPlaceholder();
    List<Product> getListProducts();
    Integer getSelectedAddressId();
    DateTime getSelectedTime();
    boolean isSelfPickup();
    void loadingAddressStart();
    void loadingAddressFinish();
    void redirectToHistory();
    String getNote();
}
