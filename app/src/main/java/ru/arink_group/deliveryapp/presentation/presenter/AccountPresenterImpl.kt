package ru.arink_group.deliveryapp.presentation.presenter

import io.reactivex.observers.DisposableObserver
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.Account
import ru.arink_group.deliveryapp.domain.dto.AccountDTO
import ru.arink_group.deliveryapp.domain.interactors.CreateAccount
import ru.arink_group.deliveryapp.domain.interactors.GetAccount
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.AccountPresenter
import ru.arink_group.deliveryapp.presentation.view.AccountView
import javax.inject.Inject

/**
 * Created by kirillvs on 31.10.17.
 */
class AccountPresenterImpl(val accountView: AccountView): AccountPresenter {

    @Inject
    lateinit var getAccount: GetAccount

    @Inject
    lateinit var createAccount: CreateAccount

    init {
        App.getComponent().inject(this)
    }

    override fun getAccount() {
        getAccount.execute(GetAccountDisposable(), GetAccount.Params())
    }

    inner class GetAccountDisposable: DisposableObserver<Account>() {

        override fun onNext(t: Account) {
            accountView.updateAccount(t)
        }

        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            accountView.showErrorMessage(e.message!!.contains("404").toString())
        }

    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
        getAccount.dispose()
    }
}