package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.Address
import ru.arink_group.deliveryapp.domain.dto.AddressDTO
import ru.arink_group.deliveryapp.domain.dto.TransformerDTO
import ru.arink_group.deliveryapp.domain.repository.AccountRepository
import javax.inject.Inject

/**
 * Created by kirillvs on 30.10.17.
 */
class UpdateAddressPatch : UseCase<Unit, UpdateAddressPatch.Params>() {

    @Inject
    lateinit var accountRepository: AccountRepository

    init {
        App.getComponent().inject(this)
    }

    override fun buildUseCaseObservable(params: Params): Observable<Unit> {
        return accountRepository.updateAddressPatch(params.address.id.toString(), params.addressDTO).map { resp: Void? -> return@map Unit  }
    }

    data class Params(val address: Address, val addressDTO: AddressDTO = TransformerDTO.transformAddressDTO(address))
}