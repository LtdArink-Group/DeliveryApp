package ru.arink_group.deliveryapp.presentation.presenter

import io.reactivex.observers.DisposableObserver
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.Account
import ru.arink_group.deliveryapp.domain.interactors.*
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.AccountPresenter
import ru.arink_group.deliveryapp.presentation.view.AccountView
import javax.inject.Inject

/**
 * Created by kirillvs on 31.10.17.
 */
class AccountPresenterImpl(val accountView: AccountView): BasePresenter(), AccountPresenter {

    @Inject
    lateinit var getAccount: GetAccount

    @Inject
    lateinit var createAccount: CreateAccount

    @Inject
    lateinit var updateAccount: UpdateAccount

    @Inject
    lateinit var deleteAddress: DeleteAddress

    var newAccount: Boolean = true

    init {
        App.getComponent().inject(this)
    }

    override fun isNewAccount(): Boolean {
        return newAccount
    }

    override fun getAccount() {
        getAccount.execute(GetAccountDisposable(), GetAccount.Params())
    }

    override fun updateAccount(account: Account) {
        val addresses = account.addresses
        account.addresses = null
        if (newAccount){
            createAccount.execute(CreateAccountDisposable(), CreateAccount.Params(account))
        } else {
            updateAccount.execute(UpdateAccountDisposable(), UpdateAccount.Params(account))
        }
    }

    override fun createAccountWithAddress(account: Account) {
        createAccount.execute(CreateAccountForAddressDisposable(), CreateAccount.Params(account))
    }


    override fun deleteAddress(id: Int) {
        deleteAddress.execute(DeleteAddressObserver(), DeleteAddress.Params(id))
    }


    inner class GetAccountDisposable: DisposableObserver<Account>() {

        override fun onNext(t: Account) {
            newAccount = false
            accountView.updateAccount(t)
        }

        override fun onComplete() {
            accountView.loadingFinished()
        }

        override fun onError(e: Throwable) {
            val error = handleGetNetError(e)
            if (error == "404") {
                val account = Account(App.getUUID())
                accountView.updateAccount(account)
                accountView.loadingFinished()
            } else {
                accountView.showErrorMessage(error)
            }

        }
    }

    inner class CreateAccountDisposable: DisposableObserver<Account>() {
        override fun onComplete() {
            newAccount = false
        }

        override fun onError(e: Throwable) {
            accountView.showErrorMessage(handlePostNetError(e))
        }

        override fun onNext(t: Account) {
            accountView.updateAccount(t)
        }

    }

    inner class CreateAccountForAddressDisposable: DisposableObserver<Account>() {
        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            accountView.showErrorMessage(handlePostNetError(e))
        }

        override fun onNext(t: Account) {
            accountView.startNewAddressAfterCreateAccount()
        }

    }

    inner class UpdateAccountDisposable: DisposableObserver<Account>() {
        override fun onError(e: Throwable) {
            accountView.showErrorMessage(handlePostNetError(e))
        }

        override fun onNext(t: Account) {
            accountView.updateAccount(t)
        }

        override fun onComplete() {
        }
    }

    inner class DeleteAddressObserver: DisposableObserver<Unit>() {
        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            accountView.showErrorMessage(handleGetNetError(e))
        }

        override fun onNext(t: Unit) {
        }
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
        getAccount.dispose()
        createAccount.dispose()
        updateAccount.dispose()
    }
}