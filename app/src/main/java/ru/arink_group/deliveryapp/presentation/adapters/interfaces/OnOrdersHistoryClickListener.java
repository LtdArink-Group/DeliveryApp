package ru.arink_group.deliveryapp.presentation.adapters.interfaces;

import ru.arink_group.deliveryapp.domain.dao.Order;

/**
 * Created by kirillvs on 21.11.17.
 */

public interface OnOrdersHistoryClickListener {
    void onOrderHistoryItemClick(Order order);
}
