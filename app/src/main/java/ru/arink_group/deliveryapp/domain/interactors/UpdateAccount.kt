package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.domain.dto.AccountDTO
import ru.arink_group.deliveryapp.domain.repository.AccountRepository
import ru.arink_group.deliveryapp.App
import javax.inject.Inject

/**
 * Created by kirillvs on 30.10.17.
 */
class UpdateAccount: UseCase<AccountDTO, UpdateAccount.Params>() {

    @Inject
    lateinit var accountRepository: AccountRepository

    init {
        App.getComponent().inject(this)
    }

    override fun buildUseCaseObservable(params: Params): Observable<AccountDTO> {
        return accountRepository.updateAccount(params.account)
    }

    data class Params(val account: AccountDTO)
}