package ru.arink_group.deliveryapp.presentation.presenter

import io.reactivex.observers.DisposableObserver
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.Address
import ru.arink_group.deliveryapp.domain.interactors.AddAddress
import ru.arink_group.deliveryapp.domain.interactors.UpdateAddress
import ru.arink_group.deliveryapp.domain.interactors.UpdateAddressPatch
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.AddressPresenter
import ru.arink_group.deliveryapp.presentation.view.AddressView
import javax.inject.Inject

/**
 * Created by kirillvs on 14.11.17.
 */
class AddressPresenterImpl(val addressView: AddressView): AddressPresenter{

    @Inject
    lateinit var addAddress: AddAddress

    @Inject
    lateinit var updateAddress: UpdateAddress

    @Inject
    lateinit var updateAddressPatch: UpdateAddressPatch


    init {
        App.getComponent().inject(this)
    }

    override fun updateAddress(address: Address) {
        if (address.id != null) {
//            updateAddress.execute(UpdateAddressDisposable(), UpdateAddress.Params(address))
            updateAddressPatch.execute(UpdateAddressPatchDisposable(), UpdateAddressPatch.Params(address))
        } else {
            addAddress.execute(AddAddressDisposable(), AddAddress.Params(address))
        }
    }

    inner class AddAddressDisposable: DisposableObserver<Address>() {
        override fun onError(e: Throwable) {
            addressView.showErrorMessage(e.message)
        }

        override fun onComplete() {
        }

        override fun onNext(t: Address) {
            addressView.goToAccount()
        }
    }

    inner class UpdateAddressDisposable: DisposableObserver<Address>() {
        override fun onComplete() {
        }

        override fun onNext(t: Address) {
            addressView.updateAddress(t)
        }

        override fun onError(e: Throwable) {
            addressView.showErrorMessage(e.message)
        }
    }

    inner class UpdateAddressPatchDisposable: DisposableObserver<Unit>() {
        override fun onNext(t: Unit) {
            addressView.goToAccount()
        }

        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            addressView.showErrorMessage("from address update ${e.message} ${e.stackTrace}")
            throw e
        }
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
        addAddress.dispose()
        updateAddress.dispose()
        updateAddressPatch.dispose()
    }



}