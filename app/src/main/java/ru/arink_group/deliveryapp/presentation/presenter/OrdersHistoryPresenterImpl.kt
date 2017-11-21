package ru.arink_group.deliveryapp.presentation.presenter

import io.reactivex.observers.DisposableObserver
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.Order
import ru.arink_group.deliveryapp.domain.interactors.GetOrders
import ru.arink_group.deliveryapp.presentation.model.Statuses
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.OrdersHistoryPresenter
import ru.arink_group.deliveryapp.presentation.view.OrdersHistoryView
import javax.inject.Inject

/**
 * Created by kirillvs on 21.11.17.
 */
class OrdersHistoryPresenterImpl(val view: OrdersHistoryView): OrdersHistoryPresenter {

    @Inject
    lateinit var getOrders: GetOrders

    init {
        App.getComponent().inject(this)
    }

    override fun getOrders() {
        view.loadingStart()
        getOrders.execute(GetOrdersDisposable(), null)
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
        getOrders.dispose()
    }

    inner class GetOrdersDisposable: DisposableObserver<List<Order>>() {
        override fun onError(e: Throwable) {
            if (e.message!!.contains("404")) {
                view.showPlaceHolder()
            } else {
                view.showErrorMessage(e.message)
            }
        }

        override fun onComplete() {
            view.loadingFinish()
        }

        override fun onNext(orders: List<Order>) {
            val activeOrders = orders.filter { it.status == Statuses.NEW }
            val completedOrders = orders.filter { it.status != Statuses.NEW }
            view.setOrders(activeOrders, completedOrders)
        }
    }
}