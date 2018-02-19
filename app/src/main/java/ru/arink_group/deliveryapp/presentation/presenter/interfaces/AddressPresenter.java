package ru.arink_group.deliveryapp.presentation.presenter.interfaces;

import ru.arink_group.deliveryapp.domain.dao.Address;

/**
 * Created by kirillvs on 14.11.17.
 */

public interface AddressPresenter extends Presenter{

    void updateAddress(Address address);

}
