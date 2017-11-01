package ru.arink_group.deliveryapp.presentation.presenter.interfaces;

import java.util.List;

import ru.arink_group.deliveryapp.domain.Account;
import ru.arink_group.deliveryapp.domain.Address;

/**
 * Created by kirillvs on 31.10.17.
 */

public interface AccountPresenter extends Presenter{

    void getAccount();

    void updateAccount(Account account);

    void updateAddress(Address address);

    void updateAddressList(List<Address> addresses);

}
