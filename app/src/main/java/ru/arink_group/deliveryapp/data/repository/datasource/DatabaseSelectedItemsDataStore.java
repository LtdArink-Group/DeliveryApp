package ru.arink_group.deliveryapp.data.repository.datasource;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.data.db.Db;
import ru.arink_group.deliveryapp.domain.SelectedProduct;

/**
 * Created by kirillvs on 09.10.17.
 */

public class DatabaseSelectedItemsDataStore implements SelectedItemsDataStore {

    Db db;

    public DatabaseSelectedItemsDataStore() {
        db = new Db();
    }

    @Override
    public Observable<SelectedProduct> addItemToBasket(SelectedProduct selectedProduct) {
        return null;
    }

    @Override
    public Observable<SelectedProduct> removeItemFromBasket(int selectedProductId) {
        return null;
    }

    @Override
    public Observable<SelectedProduct[]> getListItemsFromBasket() {
        return null;
    }
}
