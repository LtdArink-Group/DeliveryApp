package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.domain.repository.AccountRepository
import ru.arink_group.deliveryapp.App
import javax.inject.Inject

/**
 * Created by kirillvs on 30.10.17.
 */
class DeleteAddress: UseCase<Void, DeleteAddress.Params>() {

    @Inject
    lateinit var accountRepository: AccountRepository

    init {
        App.getComponent().inject(this)
    }

    override fun buildUseCaseObservable(params: Params): Observable<Void> {
        return accountRepository.deleteAddress(params.addressId.toString())
    }

    data class Params(val addressId: Int)
}