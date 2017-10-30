package ru.arink_group.deliveryapp.data.repository.factory;

import javax.inject.Inject;

import ru.arink_group.deliveryapp.data.repository.datasource.AccountDataStore;
import ru.arink_group.deliveryapp.App;

/**
 * Created by kirillvs on 30.10.17.
 */

public class AccountDataFactory {

    @Inject
    AccountDataStore accountDataStore;

    public AccountDataFactory() {
        App.getComponent().inject(this);
    }

    public AccountDataStore create() {
        return accountDataStore;
    }

}
