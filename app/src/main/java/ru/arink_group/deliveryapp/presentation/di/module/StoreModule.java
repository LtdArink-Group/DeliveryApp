package ru.arink_group.deliveryapp.presentation.di.module;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.arink_group.deliveryapp.data.net.NetAccountDataStore;
import ru.arink_group.deliveryapp.data.net.NetCompanyDataStore;
import ru.arink_group.deliveryapp.data.net.NetOrderDataStore;
import ru.arink_group.deliveryapp.data.repository.datasource.AccountDataStore;
import ru.arink_group.deliveryapp.data.repository.datasource.CompanyDataStore;
import ru.arink_group.deliveryapp.data.repository.datasource.OrderDataStore;
import ru.arink_group.deliveryapp.data.repository.datasource.implementation.DatabaseSelectedItemsDataStore;
import ru.arink_group.deliveryapp.data.repository.datasource.SelectedItemsDataStore;

/**
 * Created by kirillvs on 09.10.17.
 */

@Module
public class StoreModule {

    @Provides
    @NonNull
    public SelectedItemsDataStore selectedItemsDataStore() {
        return new DatabaseSelectedItemsDataStore();
    }

    @Provides
    @NonNull
    public AccountDataStore provideAccountDataStore() {
        return new NetAccountDataStore();
    }

    @Provides
    @NonNull
    public CompanyDataStore companyDataStore() {
        return new NetCompanyDataStore();
    }

    @Provides
    @NonNull
    public OrderDataStore orderDataStore() {
        return new NetOrderDataStore();
    }
}
