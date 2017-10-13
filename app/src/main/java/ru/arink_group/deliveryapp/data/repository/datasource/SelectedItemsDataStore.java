package ru.arink_group.deliveryapp.data.repository.datasource;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.Product;

/**
 * Created by kirillvs on 09.10.17.
 */

public interface SelectedItemsDataStore {

    public Observable<Boolean> addItemToBasket(Product selectedProduct);
    public Observable<Boolean> removeItemFromBasket(int selectedProductId);
    public Observable<List<Product>> getListItemsFromBasket();
    public Observable<Integer> addListItemsToBasket(List<Product> listItems);
}
