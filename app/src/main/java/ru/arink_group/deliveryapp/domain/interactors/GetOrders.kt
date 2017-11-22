package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.Order
import ru.arink_group.deliveryapp.domain.dto.TransformerDTO
import ru.arink_group.deliveryapp.domain.repository.OrderRepository
import javax.inject.Inject

/**
 * Created by kirillvs on 20.11.17.
 */
class GetOrders : UseCase<List<Order>, GetOrders.Params>() {

    @Inject
    lateinit var orderRepository: OrderRepository

    init {
        App.getComponent().inject(this)
    }

    override fun buildUseCaseObservable(params: Params?): Observable<List<Order>> =
            orderRepository.orders.map { TransformerDTO.transformListOrder(it) }

    class Params
}