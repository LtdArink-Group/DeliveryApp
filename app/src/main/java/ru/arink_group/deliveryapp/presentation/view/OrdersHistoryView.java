package ru.arink_group.deliveryapp.presentation.view;

import java.util.List;

import ru.arink_group.deliveryapp.domain.dao.Order;

/**
 * Created by kirillvs on 21.11.17.
 */

public interface OrdersHistoryView extends ProgressView, PlaceholderView {
    void setOrders(List<Order> activeOrders, List<Order> completedOrders);
    void showErrorMessage(String e);
}
