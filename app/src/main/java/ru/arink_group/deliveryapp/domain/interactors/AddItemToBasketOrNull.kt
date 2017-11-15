package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.domain.dao.Product
import ru.arink_group.deliveryapp.domain.repository.SelectedItemsRepository
import ru.arink_group.deliveryapp.App
import javax.inject.Inject

/**
 * Created by kirillvs on 25.10.17.
 */
class AddItemToBasketOrNull: UseCase<Product, AddItemToBasketOrNull.Params>() {

    @Inject
    lateinit var selectedItemsRepository: SelectedItemsRepository

    init {
        App.getComponent().inject(this)
    }

    override fun buildUseCaseObservable(params: Params): Observable<Product> {
        return selectedItemsRepository.addItemToBasketOrNull(params.product)
    }

    class Params(val product: Product)
}