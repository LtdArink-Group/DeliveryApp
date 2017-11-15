package ru.arink_group.deliveryapp.presentation.presenter.interfaces;

import ru.arink_group.deliveryapp.domain.dao.Account;

/**
 * Created by kirillvs on 31.10.17.
 */

public interface AccountPresenter extends Presenter{

    void getAccount();

    void updateAccount(Account account);

    void createAccountWithAddress(Account account);

    void deleteAddress(int id);

    boolean isNewAccount();

}
