package ru.arink_group.deliveryapp.data.net

import io.reactivex.Observable
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.data.net.api.BookingFoodApi
import ru.arink_group.deliveryapp.data.repository.datasource.OrderDataStore
import ru.arink_group.deliveryapp.domain.dto.OrderDTO
import java.util.*

/**
 * Created by kirillvs on 17.11.17.
 */
class NetOrderDataStore : OrderDataStore {

    private val apiService = BookingFoodApi.create()

    override fun sendOrderToServer(orderDTO: OrderDTO): Observable<Any> {
        return apiService.sendOrder(orderDTO)
    }

    override fun getOrders(): Observable<MutableList<OrderDTO>> {
        return apiService.getOrders(App.getUUID()).map { it.orders }
    }

    override fun cancelOrder(orderId: String): Observable<Any> {
        return apiService.cancelOrder(orderId)
    }
}