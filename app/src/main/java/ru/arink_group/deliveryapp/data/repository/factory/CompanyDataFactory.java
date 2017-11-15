package ru.arink_group.deliveryapp.data.repository.factory;

import javax.inject.Inject;

import ru.arink_group.deliveryapp.App;
import ru.arink_group.deliveryapp.data.repository.datasource.CompanyDataStore;

/**
 * Created by kirillvs on 15.11.17.
 */

public class CompanyDataFactory {

    @Inject
    CompanyDataStore companyDataStore;

    public CompanyDataFactory() {
        App.getComponent().inject(this);
    }

    public CompanyDataStore create() {
        return companyDataStore;
    }
}
