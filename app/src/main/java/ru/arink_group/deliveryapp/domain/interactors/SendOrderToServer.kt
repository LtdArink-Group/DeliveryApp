package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.Product
import ru.arink_group.deliveryapp.domain.dto.OrderDTO
import ru.arink_group.deliveryapp.domain.dto.TransformerDTO
import ru.arink_group.deliveryapp.domain.repository.OrderRepository
import ru.arink_group.deliveryapp.presentation.model.DateTime
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


    class Params(val products:List<Product>, val addressId: Int?, val deliveryTime: DateTime, pickup: Boolean) {
        val order: OrderDTO = TransformerDTO.createOrderDTO(products, addressId, deliveryTime, pickup)
    }
}
