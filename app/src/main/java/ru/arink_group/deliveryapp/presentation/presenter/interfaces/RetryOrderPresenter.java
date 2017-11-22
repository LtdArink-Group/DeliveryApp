package ru.arink_group.deliveryapp.presentation.presenter.interfaces;

/**
 * Created by kirillvs on 22.11.17.
 */

public interface RetryOrderPresenter {
    void getAddresses();
    void cancelOrder(String orderId);
}
