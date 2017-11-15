package ru.arink_group.deliveryapp.presentation.adapters.interfaces;

import ru.arink_group.deliveryapp.domain.dao.Address;

/**
 * Created by kirillvs on 02.11.17.
 */

public interface OnAddressListener {
    void onAddressRemove(int id, int addressesSize);
    void onAddressEdit(Address address);
}
