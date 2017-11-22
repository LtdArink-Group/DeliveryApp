package ru.arink_group.deliveryapp.presentation.presenter

import io.reactivex.observers.DisposableObserver
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.Account
import ru.arink_group.deliveryapp.domain.interactors.CancelOrder
import ru.arink_group.deliveryapp.domain.interactors.GetAccount
import ru.arink_group.deliveryapp.presentation.model.ErrorsTranslator
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

    init {
        App.getComponent().inject(this)
    }


    override fun getAddresses() {
        getAccount.execute(AccountDisposableObserver(), GetAccount.Params())
    }

    override fun cancelOrder(orderId: String) {
        view.startButtonAnimation()
        cancelOrder.execute(CancelOrderObserver(), CancelOrder.Param(orderId))
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

}