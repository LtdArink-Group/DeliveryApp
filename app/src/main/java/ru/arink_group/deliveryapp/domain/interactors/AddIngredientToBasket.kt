package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.domain.dao.Ingredient
import ru.arink_group.deliveryapp.domain.repository.SelectedItemsRepository
import ru.arink_group.deliveryapp.App
import javax.inject.Inject

/**
 * Created by kirillvs on 17.10.17.
 */
class AddIngredientToBasket: UseCase<Boolean, AddIngredientToBasket.Params>() {

    init {
        App.getComponent().inject(this)
    }

    @Inject
    lateinit var selectedItemRepository: SelectedItemsRepository


    override fun buildUseCaseObservable(params: Params): Observable<Boolean> {
        return selectedItemRepository.addIngredientToProduct(params.productId, params.ingredient)
    }


    class Params constructor(val productId: Int, val ingredient: Ingredient)
}