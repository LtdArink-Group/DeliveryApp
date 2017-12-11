package ru.arink_group.deliveryapp.presentation.view;

import ru.arink_group.deliveryapp.domain.dao.Address;

/**
 * Created by kirillvs on 14.11.17.
 */

public interface AddressView extends ProgressView{

    void showErrorMessage(String message);
    void updateAddress(Address address);
    void goToAccount();
    void goToOrder();
    boolean isStartOrder();
}
