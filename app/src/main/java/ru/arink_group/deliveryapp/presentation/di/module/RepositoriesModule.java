package ru.arink_group.deliveryapp.presentation.di.module;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.arink_group.deliveryapp.data.repository.AccountDataRepository;
import ru.arink_group.deliveryapp.data.repository.CompanyDataRepository;
import ru.arink_group.deliveryapp.data.repository.OrderDataRepository;
import ru.arink_group.deliveryapp.data.repository.SelectedItemsDataRepository;
import ru.arink_group.deliveryapp.domain.repository.AccountRepository;
import ru.arink_group.deliveryapp.domain.repository.CompanyRepository;
import ru.arink_group.deliveryapp.domain.repository.OrderRepository;
import ru.arink_group.deliveryapp.domain.repository.SelectedItemsRepository;

/**
 * Created by kirillvs on 09.10.17.
 */

@Module
public class RepositoriesModule {

    @Provides
    @NonNull
    public SelectedItemsRepository provideSelectedItemsRepository() {
        return new SelectedItemsDataRepository();
    }

    @Provides
    @NonNull
    public AccountRepository accountRepository() {
        return new AccountDataRepository();
    }

    @Provides
    @NonNull
    public CompanyRepository companyRepository() {
        return new CompanyDataRepository();
    }

    @Provides
    @NonNull
    public OrderRepository orderRepository() {
        return new OrderDataRepository();
    }

}
