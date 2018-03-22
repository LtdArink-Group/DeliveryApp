package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.domain.dto.AccountDTO
import ru.arink_group.deliveryapp.domain.repository.AccountRepository
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.Account
import ru.arink_group.deliveryapp.domain.dto.DeviceDTO
import ru.arink_group.deliveryapp.domain.dto.TransformerDTO
import javax.inject.Inject

/**
 * Created by kirillvs on 30.10.17.
 */
class RegisterDevice : UseCase<Void, RegisterDevice.Params>() {

    @Inject
    lateinit var accountRepository: AccountRepository

    init {
        App.getComponent().inject(this)
    }

    override fun buildUseCaseObservable(params: Params): Observable<Void> {
        return accountRepository.registerDevice(params.deviceDTO)
    }

    class Params(registrationToken: String) {
        val deviceDTO: DeviceDTO = TransformerDTO.transformDeviceDTO(registrationToken)
    }

}