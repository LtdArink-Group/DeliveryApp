package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.domain.dto.AddressDTO
import ru.arink_group.deliveryapp.domain.repository.AccountRepository
import ru.arink_group.deliveryapp.App
import javax.inject.Inject

/**
 * Created by kirillvs on 30.10.17.
 */
class AddAddress: UseCase<AddressDTO, AddAddress.Params>() {

    @Inject
    lateinit var accountRepository: AccountRepository

    init {
        App.getComponent().inject(this)
    }

    override fun buildUseCaseObservable(params: Params): Observable<AddressDTO> {
        return accountRepository.addAddress(params.address)
    }

    data class Params(val address: AddressDTO)
}