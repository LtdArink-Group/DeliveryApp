package ru.arink_group.deliveryapp.presentation.view;

import ru.arink_group.deliveryapp.domain.Account;

/**
 * Created by kirillvs on 31.10.17.
 */

public interface AccountView {
    void showErrorMessage(String message);
    void updateAccount(Account account);
}
