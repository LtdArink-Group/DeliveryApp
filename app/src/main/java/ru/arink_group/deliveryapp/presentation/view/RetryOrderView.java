package ru.arink_group.deliveryapp.presentation.view;

import java.util.List;

import ru.arink_group.deliveryapp.domain.dao.Address;
import ru.arink_group.deliveryapp.domain.dao.Order;
import ru.arink_group.deliveryapp.presentation.shared.DateTime;

/**
 * Created by kirillvs on 22.11.17.
 */

public interface RetryOrderView {
    void showErrorMessage(String e);
    void showSendingOrderOk();
    void startButtonAnimation();
    void stopButtonAnimationWithError(String error);
    void showSendingOrderFail(String error);
    void updateAddresses(List<Address> addresses);
    void redirectToHistory();

    void loadingAddressStart();
    void loadingAddressFinish();

    Order getVerifyedOrder();
    Integer getAddressId();
    DateTime getDeliveryTime();
    String getNote();

}
