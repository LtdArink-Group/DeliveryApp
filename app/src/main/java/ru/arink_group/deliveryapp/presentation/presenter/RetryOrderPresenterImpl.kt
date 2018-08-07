package ru.arink_group.deliveryapp.presentation.presenter

import io.reactivex.observers.DisposableObserver
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.Account
import ru.arink_group.deliveryapp.domain.interactors.CancelOrder
import ru.arink_group.deliveryapp.domain.interactors.GetAccount
import ru.arink_group.deliveryapp.domain.interactors.GetCompanyFromShared
import ru.arink_group.deliveryapp.domain.interactors.SendOrderToServer
import ru.arink_group.deliveryapp.presentation.shared.ErrorsTranslator
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.RetryOrderPresenter
import ru.arink_group.deliveryapp.presentation.view.RetryOrderView
import javax.inject.Inject

/**
 * Created by kirillvs on 22.11.17.
 */
class RetryOrderPresenterImpl(val view: RetryOrderView): BasePresenter(), RetryOrderPresenter {

    @Inject
    lateinit var getAccount: GetAccount

    @Inject
    lateinit var cancelOrder: CancelOrder

    @Inject
    lateinit var sendOrderToServer: SendOrderToServer

    init {
        App.getComponent().inject(this)
    }


    override fun getAddresses(isCompanyAddress: Boolean) {
        if(isCompanyAddress) {
            view.loadingAddressFinish()
            view.updateAddresses(GetCompanyFromShared.getCompanyOrDefault().addresses)
        } else {
            getAccount.execute(AccountDisposableObserver(), GetAccount.Params())
        }
    }

    override fun cancelOrder(orderId: String) {
        view.startButtonAnimation()
        cancelOrder.execute(CancelOrderObserver(), CancelOrder.Param(orderId))
    }

    override fun sendOrderToServer() {
        sendOrderToServer
                .execute(
                        SendOrderToServerDisposableObserver(),
                        SendOrderToServer.Params(
                                view.verifyedOrder,
                                view.addressId,
                                view.deliveryTime,
                                view.note
                        )
                )
    }

    inner class AccountDisposableObserver: DisposableObserver<Account>() {
        override fun onError(e: Throwable) {
            val error = handleGetNetError(e)
            if (error == "404") {
                view.showErrorMessage(ErrorsTranslator.translate("empty_address"))
            } else {
                view.showErrorMessage(error)
            }
        }

        override fun onComplete() {
            view.loadingAddressFinish()
        }

        override fun onNext(t: Account) {
            view.updateAddresses(t.addresses)
        }
    }

    inner class CancelOrderObserver: DisposableObserver<Any>() {

        override fun onError(e: Throwable) {
            view.stopButtonAnimationWithError(handlePostNetError(e))
        }

        override fun onNext(t: Any) {
        }

        override fun onComplete() {
            view.redirectToHistory()
        }
    }

    inner class SendOrderToServerDisposableObserver: DisposableObserver<Boolean>() {
        override fun onError(e: Throwable) {
            view.stopButtonAnimationWithError(handlePostNetError(e))
        }

        override fun onComplete() {
            view.redirectToHistory()
        }

        override fun onNext(t: Boolean) {
            // --no-op
        }

    }


}