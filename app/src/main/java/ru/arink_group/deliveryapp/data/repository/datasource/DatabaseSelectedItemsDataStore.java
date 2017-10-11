package ru.arink_group.deliveryapp.data.repository.datasource;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
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
    public Observable<SelectedProduct> addItemToBasket(final SelectedProduct selectedProduct) {
        return Observable.create(new ObservableOnSubscribe<SelectedProduct>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<SelectedProduct> e) throws Exception {
                e.onNext(db.addItemToBasket(selectedProduct));
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<SelectedProduct> removeItemFromBasket(final int selectedProductId) {
        return Observable.create(new ObservableOnSubscribe<SelectedProduct>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<SelectedProduct> e) throws Exception {
                e.onNext(db.removeItemFromBasket(selectedProductId));
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<List<SelectedProduct>> getListItemsFromBasket() {
        return Observable.create(new ObservableOnSubscribe<List<SelectedProduct>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<SelectedProduct>> e) throws Exception {
                e.onNext(db.getListItemsFromBasket());
                e.onComplete();
            }
        });
    }
}
