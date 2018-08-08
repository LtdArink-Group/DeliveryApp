package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.Order
import ru.arink_group.deliveryapp.domain.dao.Product
import ru.arink_group.deliveryapp.domain.dto.OrderDTO
import ru.arink_group.deliveryapp.domain.dto.TransformerDTO
import ru.arink_group.deliveryapp.domain.repository.OrderRepository
import ru.arink_group.deliveryapp.presentation.shared.DateTime
import javax.inject.Inject

/**
 * Created by kirillvs on 24.10.17.
 */
class SendOrderToServer: UseCase<Boolean, SendOrderToServer.Params>() {

    @Inject
    lateinit var orderRepository: OrderRepository

    init {
        App.getComponent().inject(this)
    }

    override fun buildUseCaseObservable(params: Params): Observable<Boolean> {
        return orderRepository.sendOrder(params.order).map { true }
    }


    class Params {
        val order: OrderDTO
        constructor(products:List<Product>, addressId: Int?, deliveryTime: DateTime, pickup: Boolean, note: String) {
            order = TransformerDTO.createOrderDTO(products, addressId, deliveryTime, pickup, note)
        }
        constructor(orderToSent: Order, addressId: Int?, deliveryTime: DateTime, note: String) {
            order = TransformerDTO.trahsformOrderDTO(orderToSent, addressId, deliveryTime, note)
        }
    }
}
