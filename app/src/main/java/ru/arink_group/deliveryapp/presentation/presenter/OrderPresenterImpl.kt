package ru.arink_group.deliveryapp.presentation.presenter

import io.reactivex.observers.DisposableObserver
import ru.arink_group.deliveryapp.domain.Product
import ru.arink_group.deliveryapp.domain.interactors.*
import ru.arink_group.deliveryapp.presentation.App
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.OrderPresenter
import ru.arink_group.deliveryapp.presentation.view.OrderView
import javax.inject.Inject

/**
 * Created by kirillvs on 24.10.17.
 */
class OrderPresenterImpl(val orderView: OrderView): OrderPresenter {

    @Inject
    lateinit var getListItemsFromBasket:GetListItemsFromBasket

    @Inject
    lateinit var addItemToBasketOrNull:AddItemToBasketOrNull

    @Inject
    lateinit var sendOrderToServer: SendOrderToServer

    @Inject
    lateinit var clearItemsFromBasket: ClearItemsFromBasket


    init {
        App.getComponent().inject(this)
    }

    override fun getTotals() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
        getListItemsFromBasket.dispose()
        addItemToBasketOrNull.dispose()
    }

    override fun getProductsFromBasket() {
        getListItemsFromBasket.execute(ProductsDisposableObserver(), GetListItemsFromBasket.Params())
    }

    override fun updateProduct(product: Product) {
        addItemToBasketOrNull.execute(UpdateProductDisposableObserver(), AddItemToBasketOrNull.Params(product))
    }

    override fun sendOrderToServer() {
        sendOrderToServer.execute(SendOrderToServerDisposableObserver(), SendOrderToServer.Params(orderView.listProducts))
    }

    inner class ProductsDisposableObserver: DisposableObserver<List<Product>>() {

        override fun onError(e: Throwable) {
            orderView.showErrorMessage(e.message)
        }

        override fun onComplete() {
            orderView.updateTotals()
        }

        override fun onNext(t: List<Product>) {
            orderView.setProducts(t)
        }

    }

    inner class UpdateProductDisposableObserver: DisposableObserver<Product>() {

        override fun onComplete() {
            orderView.updateTotals()
        }

        override fun onError(e: Throwable) {
            orderView.showErrorMessage(e.message)
        }

        override fun onNext(t: Product) {
            orderView.updateProductState(t)
        }

    }

    inner class SendOrderToServerDisposableObserver: DisposableObserver<Boolean>() {
        override fun onError(e: Throwable) {
            orderView.showErrorMessage(e.message)
        }

        override fun onComplete() {
            clearItemsFromBasket.execute(ClearItemsFromBasketDisposableObserver(), ClearItemsFromBasket.Params())
        }

        override fun onNext(t: Boolean) {
            orderView.showSendingOrderOk()
        }

    }

    inner class ClearItemsFromBasketDisposableObserver: DisposableObserver<Boolean>() {
        override fun onComplete() {
            orderView.showPlaceholder()
        }

        override fun onError(e: Throwable) {
            orderView.showErrorMessage(e.message)
        }

        override fun onNext(t: Boolean) {
        }

    }
}