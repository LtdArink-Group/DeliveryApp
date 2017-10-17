package ru.arink_group.deliveryapp.presentation.presenter

import io.reactivex.observers.DisposableObserver
import ru.arink_group.deliveryapp.domain.Ingredient
import ru.arink_group.deliveryapp.domain.Product
import ru.arink_group.deliveryapp.domain.interactors.AddIngredientToBasket
import ru.arink_group.deliveryapp.domain.interactors.AddItemToBasket
import ru.arink_group.deliveryapp.presentation.App
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.IngredientsPresenter
import ru.arink_group.deliveryapp.presentation.view.IngredientsView
import javax.inject.Inject

/**
 * Created by kirillvs on 17.10.17.
 */

class IngredientsPresenterImpl(internal val ingredientsView: IngredientsView) : IngredientsPresenter {

    @Inject
    lateinit var addIngredientToBasket: AddIngredientToBasket

    init {
        App.getComponent().inject(this)
    }

    override fun fetchProduct(productId: Int) {

    }

    override fun updateProduct(product: Product) {
    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun destroy() {
        addIngredientToBasket.dispose()
    }

    override fun addIngredientToBasket(productId: Int, ingredient: Ingredient) {
        addIngredientToBasket.execute(IngredientDisposableObserver(), AddIngredientToBasket.Params(productId, ingredient))
    }

    inner class IngredientDisposableObserver : DisposableObserver<Boolean>() {
        override fun onNext(t: Boolean) {
            ingredientsView.showErrorMessage(t.toString())
        }

        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            ingredientsView.showErrorMessage(e.message)
        }

    }
}
