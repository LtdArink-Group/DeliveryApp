package ru.arink_group.deliveryapp.data.repository.datasource;

import javax.inject.Inject;

import ru.arink_group.deliveryapp.presentation.App;

/**
 * Created by kirillvs on 09.10.17.
 */

public class SelectedItemsDataFactory {

    @Inject public SelectedItemsDataStore selectedItemsDataStore;

    public SelectedItemsDataFactory() {
        App.getComponent().inject(this);

    }

    public SelectedItemsDataStore create() {
        return selectedItemsDataStore;
    }
}
