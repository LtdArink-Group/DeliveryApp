package ru.arink_group.deliveryapp.data.net

import io.reactivex.Observable
import ru.arink_group.deliveryapp.data.net.api.BookingFoodApi
import ru.arink_group.deliveryapp.data.repository.datasource.ProductDataStore
import ru.arink_group.deliveryapp.domain.dto.ProductDTO
import ru.arink_group.deliveryapp.presentation.App

/**
 * Created by kirillvs on 20.10.17.
 */
class NetProductDataStore : ProductDataStore {
    override fun productsDataList(categoryId: Int): Observable<List<ProductDTO>> {
        val apiService = BookingFoodApi.create()
        return apiService.products(App.getCompanyId(), categoryId.toString()).map { it.products  }
    }

    override fun productData(productId: Int): Observable<ProductDTO> {
        val apiService = BookingFoodApi.create()
        return apiService.product(productId.toString())
    }
}