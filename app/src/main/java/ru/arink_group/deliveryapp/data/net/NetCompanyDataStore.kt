package ru.arink_group.deliveryapp.data.net

import io.reactivex.Observable
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.data.net.api.BookingFoodApi
import ru.arink_group.deliveryapp.data.repository.datasource.CompanyDataStore
import ru.arink_group.deliveryapp.domain.dto.CompanyDTO

/**
 * Created by kirillvs on 15.11.17.
 */
class NetCompanyDataStore: CompanyDataStore {

    private val apiService = BookingFoodApi.create()

    override fun getCompany(): Observable<CompanyDTO> {
        return apiService.getCompany(App.getCompanyId())
    }
}