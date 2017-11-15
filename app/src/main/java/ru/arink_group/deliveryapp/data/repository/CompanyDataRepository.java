package ru.arink_group.deliveryapp.data.repository;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.App;
import ru.arink_group.deliveryapp.data.repository.factory.CompanyDataFactory;
import ru.arink_group.deliveryapp.domain.dto.CompanyDTO;
import ru.arink_group.deliveryapp.domain.repository.CompanyRepository;

/**
 * Created by kirillvs on 15.11.17.
 */

public class CompanyDataRepository implements CompanyRepository {

    @Inject
    CompanyDataFactory companyDataFactory;

    public CompanyDataRepository() {
        App.getComponent().inject(this);
    }

    @Override
    public Observable<CompanyDTO> getCompany() {
        return companyDataFactory.create().getCompany();
    }
}
