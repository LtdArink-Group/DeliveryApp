package ru.arink_group.deliveryapp.data.repository.datasource;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.SelectedProduct;

/**
 * Created by kirillvs on 09.10.17.
 */

public interface SelectedItemsDataStore {

    public Observable<SelectedProduct> addItemToBasket(SelectedProduct selectedProduct);
    public Observable<SelectedProduct> removeItemFromBasket(int selectedProductId);
    public Observable<List<SelectedProduct>> getListItemsFromBasket();
}
