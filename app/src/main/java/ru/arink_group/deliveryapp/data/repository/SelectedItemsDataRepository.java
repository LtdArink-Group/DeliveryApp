package ru.arink_group.deliveryapp.data.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.data.repository.datasource.SelectedItemsDataFactory;
import ru.arink_group.deliveryapp.domain.Ingredient;
import ru.arink_group.deliveryapp.domain.Product;
import ru.arink_group.deliveryapp.domain.repository.SelectedItemsRepository;
import ru.arink_group.deliveryapp.presentation.App;

/**
 * Created by kirillvs on 09.10.17.
 */

public class SelectedItemsDataRepository implements SelectedItemsRepository {

    public @Inject
    SelectedItemsDataFactory factory;

    public SelectedItemsDataRepository() {
        App.getComponent().inject(this);
    }

    @Override
    public Observable<Boolean> addItemToBasket(Product selectedProduct) {
        return factory.create().addItemToBasket(selectedProduct);
    }

    @Override
    public Observable<Boolean> removeItemFromBasket(int selectedProductId) {
        return factory.create().removeItemFromBasket(selectedProductId);
    }

    @Override
    public Observable<List<Product>> getListItemsFromBasket() {
        return factory.create().getListItemsFromBasket();
    }

    @Override
    public Observable<Boolean> addIngredientToProduct(int productId, Ingredient ingredient) {
        return factory.create().addIngredientToProduct(productId, ingredient);
    }
}
