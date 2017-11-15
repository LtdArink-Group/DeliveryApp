package ru.arink_group.deliveryapp.presentation.di.module;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.arink_group.deliveryapp.data.repository.factory.AccountDataFactory;
import ru.arink_group.deliveryapp.data.repository.factory.CompanyDataFactory;
import ru.arink_group.deliveryapp.data.repository.factory.SelectedItemsDataFactory;

/**
 * Created by kirillvs on 09.10.17.
 */

@Module
public class FactoriesModule {

    @Provides
    @NonNull
    public SelectedItemsDataFactory selectedItemsDataFactory() {
        return new SelectedItemsDataFactory();
    }

    @Provides
    @NonNull
    public AccountDataFactory accountDataFactory() {
        return new AccountDataFactory();
    }

    @Provides
    @NonNull
    public CompanyDataFactory companyDataFactory() {
        return new CompanyDataFactory();
    }
}
