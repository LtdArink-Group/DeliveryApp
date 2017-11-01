package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.domain.dto.AddressDTO
import ru.arink_group.deliveryapp.domain.repository.AccountRepository
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.Address
import ru.arink_group.deliveryapp.domain.dto.TransformerDTO
import javax.inject.Inject

/**
 * Created by kirillvs on 30.10.17.
 */
class UpdateAddress: UseCase<Address, UpdateAddress.Params>() {

    @Inject
    lateinit var accountRepository: AccountRepository

    init {
        App.getComponent().inject(this)
    }

    override fun buildUseCaseObservable(params: Params): Observable<Address> {
        return accountRepository.updateAddress(params.address.id.toString(), params.addressDTO).map { TransformerDTO.transformAddress(it) }
    }

    data class Params(val address: Address, val addressDTO: AddressDTO = TransformerDTO.transformAddressDTO(address))
}