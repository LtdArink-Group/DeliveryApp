package ru.arink_group.deliveryapp.presentation.presenter

import io.reactivex.observers.DisposableObserver
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.Account
import ru.arink_group.deliveryapp.domain.Address
import ru.arink_group.deliveryapp.domain.interactors.*
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

    @Inject
    lateinit var updateAccount: UpdateAccount

    @Inject
    lateinit var addAddress: AddAddress

    @Inject
    lateinit var updateAddress: UpdateAddress

    @Inject
    lateinit var updateAddressPatch: UpdateAddressPatch

    @Inject
    lateinit var deleteAddress: DeleteAddress

    var newAccount: Boolean = true

    init {
        App.getComponent().inject(this)
    }

    override fun getAccount() {
        getAccount.execute(GetAccountDisposable(), GetAccount.Params())
    }

    override fun updateAccount(account: Account) {
        val addresses = account.addresses
        account.addresses = null
        if (newAccount){
            createAccount.execute(CreateAccountDisposable(addresses!!.toMutableList()), CreateAccount.Params(account))
        } else {
            updateAccount.execute(UpdateAccountDisposable(), UpdateAccount.Params(account))
            updateAddressList(addresses!!.toMutableList())
        }
    }

    override fun updateAddress(address: Address) {
        if (address.id != null) {
//            updateAddress.execute(UpdateAddressDisposable(), UpdateAddress.Params(address))
            updateAddressPatch.execute(UpdateAddressPatchDisposable(), UpdateAddressPatch.Params(address))
        } else {
            addAddress.execute(AddAddressDisposable(), AddAddress.Params(address))
        }
    }

    override fun deleteAddress(id: Int) {
        deleteAddress.execute(DeleteAddressObserver(), DeleteAddress.Params(id))
    }


    override fun updateAddressList(addresses: MutableList<Address>) {
        addresses.forEach { updateAddress(it) }
    }

    inner class GetAccountDisposable: DisposableObserver<Account>() {

        override fun onNext(t: Account) {
            newAccount = false
            accountView.updateAccount(t)
        }

        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            if (e.message!!.contains("404")) {
                val account = Account(App.getUUID())
                accountView.updateAccount(account)
            } else {
                accountView.showErrorMessage(e.message)
            }

        }
    }

    inner class CreateAccountDisposable(val addresses: MutableList<Address>): DisposableObserver<Account>() {
        override fun onComplete() {
            updateAddressList(addresses)
        }

        override fun onError(e: Throwable) {
            accountView.showErrorMessage(e.message)
        }

        override fun onNext(t: Account) {
            accountView.updateAccount(t)
        }

    }

    inner class UpdateAccountDisposable: DisposableObserver<Account>() {
        override fun onError(e: Throwable) {
            accountView.showErrorMessage(e.message)
        }

        override fun onNext(t: Account) {
            accountView.updateAccount(t)
        }

        override fun onComplete() {
        }
    }

    inner class AddAddressDisposable: DisposableObserver<Address>() {
        override fun onError(e: Throwable) {
            accountView.showErrorMessage(e.message)
        }

        override fun onComplete() {
        }

        override fun onNext(t: Address) {
            accountView.updateAddress(t)
        }
    }

    inner class UpdateAddressDisposable: DisposableObserver<Address>() {
        override fun onComplete() {
        }

        override fun onNext(t: Address) {
            accountView.updateAddress(t)
        }

        override fun onError(e: Throwable) {
            accountView.showErrorMessage(e.message)
        }
    }

    inner class UpdateAddressPatchDisposable: DisposableObserver<Unit>() {
        override fun onNext(t: Unit) {
        }

        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            accountView.showErrorMessage("from address update ${e.message} ${e.stackTrace}")
            throw e
        }
    }

    inner class DeleteAddressObserver: DisposableObserver<Unit>() {
        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            accountView.showErrorMessage(e.message)
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
        addAddress.dispose()
        updateAddress.dispose()
        updateAccount.dispose()
        updateAddressPatch.dispose()
    }
}