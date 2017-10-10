package ru.arink_group.deliveryapp.presentation.di.module;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.arink_group.deliveryapp.data.repository.datasource.DatabaseSelectedItemsDataStore;
import ru.arink_group.deliveryapp.data.repository.datasource.FakeSelectedItemsDataStore;
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
}
