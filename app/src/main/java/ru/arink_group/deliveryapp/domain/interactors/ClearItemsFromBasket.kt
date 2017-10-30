package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.domain.repository.SelectedItemsRepository
import ru.arink_group.deliveryapp.App
import javax.inject.Inject

/**
 * Created by kirillvs on 26.10.17.
 */
class ClearItemsFromBasket: UseCase<Boolean, ClearItemsFromBasket.Params>() {

    @Inject
    lateinit var selectedItemsRepository: SelectedItemsRepository

    init {
        App.getComponent().inject(this)
    }

    override fun buildUseCaseObservable(params: Params?): Observable<Boolean> {
        return selectedItemsRepository.clearItemsFromBasket()
    }

    class Params()
}