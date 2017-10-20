package ru.arink_group.deliveryapp.data.net

import io.reactivex.Observable
import ru.arink_group.deliveryapp.data.net.api.BookingFoodApi
import ru.arink_group.deliveryapp.data.repository.datasource.CategoryDataStore
import ru.arink_group.deliveryapp.domain.dto.CategoryDTO
import ru.arink_group.deliveryapp.presentation.App

/**
 * Created by kirillvs on 20.10.17.
 */
class NetCategoryDataStore : CategoryDataStore {

    override fun categoriesDataList(): Observable<List<CategoryDTO>> {
        val apiService = BookingFoodApi.create()
        return apiService.categories(App.getCompanyId()).map { it.categories  }; // TODO Where to keep company id?
    }

}