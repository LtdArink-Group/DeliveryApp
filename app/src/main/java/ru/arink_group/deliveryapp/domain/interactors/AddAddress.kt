package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.domain.dto.AddressDTO
import ru.arink_group.deliveryapp.domain.repository.AccountRepository
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.Address
import ru.arink_group.deliveryapp.domain.dto.TransformerDTO
import javax.inject.Inject

/**
 * Created by kirillvs on 30.10.17.
 */
class AddAddress: UseCase<Address, AddAddress.Params>() {

    @Inject
    lateinit var accountRepository: AccountRepository

    init {
        App.getComponent().inject(this)
    }

    override fun buildUseCaseObservable(params: Params): Observable<Address> {
        return accountRepository.addAddress(params.addressDTO).map { TransformerDTO.transformAddress(it) }
    }

    data class Params(val address: Address, val addressDTO: AddressDTO = TransformerDTO.transformAddressDTO(address))
}