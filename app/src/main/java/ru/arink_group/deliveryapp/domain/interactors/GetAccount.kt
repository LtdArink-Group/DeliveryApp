package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.domain.repository.AccountRepository
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.Account
import ru.arink_group.deliveryapp.domain.dto.TransformerDTO
import javax.inject.Inject

/**
 * Created by kirillvs on 30.10.17.
 */
class GetAccount: UseCase<Account, GetAccount.Params>() {

    @Inject
    lateinit var accountRepository: AccountRepository

    init {
        App.getComponent().inject(this)
    }

    override fun buildUseCaseObservable(params: Params): Observable<Account> {
        return accountRepository.account.map { TransformerDTO.transformAccount(it) }
    }

    class Params
}