package ru.arink_group.deliveryapp.presentation.presenter

import io.reactivex.observers.DisposableObserver
import ru.arink_group.deliveryapp.domain.dao.Product
import ru.arink_group.deliveryapp.domain.interactors.*
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.Account
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.OrderPresenter
import ru.arink_group.deliveryapp.presentation.view.OrderView
import javax.inject.Inject


/**
 * Created by kirillvs on 24.10.17.
 */
class OrderPresenterImpl(val orderView: OrderView): BasePresenter(), OrderPresenter {
    @Inject
    lateinit var getListItemsFromBasket:GetListItemsFromBasket

    @Inject
    lateinit var addItemToBasketOrNull:AddItemToBasketOrNull

    @Inject
    lateinit var sendOrderToServer: SendOrderToServer

    @Inject
    lateinit var clearItemsFromBasket: ClearItemsFromBasket

    @Inject
    lateinit var getAccount: GetAccount


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
        getAccount.dispose()
    }

    override fun getProductsFromBasket() {
        orderView.loadingStart()
        getListItemsFromBasket.execute(ProductsDisposableObserver(), GetListItemsFromBasket.Params())
    }

    override fun updateProduct(product: Product) {
        addItemToBasketOrNull.execute(UpdateProductDisposableObserver(), AddItemToBasketOrNull.Params(product))
    }

    override fun sendOrderToServer() {
        sendOrderToServer
                .execute(
                        SendOrderToServerDisposableObserver(),
                        SendOrderToServer.Params(
                                orderView.listProducts,
                                orderView.selectedAddressId,
                                orderView.selectedTime,
                                orderView.isSelfPickup,
                                orderView.note
                        )
                )
    }

    override fun getAddresses(isCompanyAddreses: Boolean) {
        if(isCompanyAddreses) {
            orderView.loadingAddressFinish()
            orderView.updateAddresses(GetCompanyFromShared.getCompanyOrDefault().addresses)
        } else {
            getAccount.execute(AccountDisposableObserver(), GetAccount.Params())
        }
    }

    inner class AccountDisposableObserver: DisposableObserver<Account>() {
        override fun onError(e: Throwable) {
            val error = handleGetNetError(e)
            if (error == "404") {
                orderView.showCreateAccountButton()
                orderView.loadingAddressFinish()
            } else {
                orderView.showErrorMessage(error)
            }
        }

        override fun onComplete() {
            orderView.loadingAddressFinish()
        }

        override fun onNext(t: Account) {
            orderView.updateAddresses(t.addresses)
        }
    }

    inner class ProductsDisposableObserver: DisposableObserver<List<Product>>() {

        override fun onError(e: Throwable) {
            orderView.showErrorMessage(handleGetNetError(e))
        }

        override fun onComplete() {
            orderView.loadingFinish()
        }

        override fun onNext(t: List<Product>) {
            if (t.isEmpty()) {
                orderView.showPlaceholder()
            } else {
                orderView.setProducts(t)
                getAddresses(false)
                orderView.updateTotals()
            }
        }

    }

    inner class UpdateProductDisposableObserver: DisposableObserver<Product>() {

        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            orderView.showErrorMessage(handleInternalError(e))
        }

        override fun onNext(t: Product) {
            orderView.updateProductState(t)
        }

    }

    inner class SendOrderToServerDisposableObserver: DisposableObserver<Boolean>() {
        override fun onError(e: Throwable) {
            orderView.showErrorMessage(handlePostNetError(e))
        }

        override fun onComplete() {
            clearItemsFromBasket.execute(ClearItemsFromBasketDisposableObserver(), ClearItemsFromBasket.Params())
            orderView.showSendingOrderOk()
            orderView.redirectToHistory()
        }

        override fun onNext(t: Boolean) {
            // --no-op
        }

    }

    inner class ClearItemsFromBasketDisposableObserver: DisposableObserver<Boolean>() {
        override fun onComplete() {
//            orderView.showPlaceholder()
        }

        override fun onError(e: Throwable) {
            orderView.showErrorMessage(handleInternalError(e))
        }

        override fun onNext(t: Boolean) {
        }

    }
}