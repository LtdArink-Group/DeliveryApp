package ru.arink_group.deliveryapp.data.repository.datasource;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.Ingredient;
import ru.arink_group.deliveryapp.domain.Product;

/**
 * Created by kirillvs on 09.10.17.
 */

public interface SelectedItemsDataStore {

    Observable<Boolean> addItemToBasket(Product selectedProduct);
    Observable<Boolean> removeItemFromBasket(int selectedProductId);
    Observable<List<Product>> getListItemsFromBasket();
    Observable<Integer> addListItemsToBasket(List<Product> listItems);
    Observable<Boolean> addIngredientToProduct(int productId, Ingredient ingredient);
}
