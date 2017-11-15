package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.Company
import ru.arink_group.deliveryapp.domain.dto.TransformerDTO
import ru.arink_group.deliveryapp.domain.repository.CompanyRepository
import javax.inject.Inject

/**
 * Created by kirillvs on 15.11.17.
 */
class GetCompany: UseCase<Company, GetCompany.Param>() {

    @Inject
    lateinit var companyRepository: CompanyRepository

    init {
        App.getComponent().inject(this)
    }

    override fun buildUseCaseObservable(params: Param?): Observable<Company> {
        return companyRepository.company.map { TransformerDTO.transformCompany(it) }
    }

    class Param
}