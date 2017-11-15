package ru.arink_group.deliveryapp.domain.repository;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.dto.CompanyDTO;

/**
 * Created by kirillvs on 15.11.17.
 */

public interface CompanyRepository {
    Observable<CompanyDTO> getCompany();
}
