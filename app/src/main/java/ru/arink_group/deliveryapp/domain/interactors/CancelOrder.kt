package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.repository.OrderRepository
import javax.inject.Inject

/**
 * Created by kirillvs on 22.11.17.
 */
class CancelOrder: UseCase<Any, CancelOrder.Param>() {

    @Inject
    lateinit var orderRepository: OrderRepository

    init {
        App.getComponent().inject(this)
    }

    override fun buildUseCaseObservable(params: Param): Observable<Any> {
        return orderRepository.cancelOrder(params.orderId)
    }

    data class Param(val orderId: String)
}