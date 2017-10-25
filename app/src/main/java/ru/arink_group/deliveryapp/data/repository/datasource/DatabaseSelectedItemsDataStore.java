package ru.arink_group.deliveryapp.data.repository.datasource;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import ru.arink_group.deliveryapp.data.db.Db;
import ru.arink_group.deliveryapp.domain.Ingredient;
import ru.arink_group.deliveryapp.domain.Product;

/**
 * Created by kirillvs on 09.10.17.
 */

public class DatabaseSelectedItemsDataStore implements SelectedItemsDataStore {

    Db db;

    public DatabaseSelectedItemsDataStore() {
        db = new Db();
    }


    @Override
    public Observable<Boolean> addItemToBasket(final Product selectedProduct) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> e) throws Exception {
                e.onNext(db.addItemToBasket(selectedProduct));
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<Product> addItemToBasketOrNull(final Product selectedProduct) {
        return Observable.create(new ObservableOnSubscribe<Product>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Product> e) throws Exception {
                e.onNext(db.addItemToBasketOrNull(selectedProduct));
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> removeItemFromBasket(final int selectedProductId) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> e) throws Exception {
                e.onNext(db.removeItemFromBasket(selectedProductId));
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<List<Product>> getListItemsFromBasket() {
        return Observable.create(new ObservableOnSubscribe<List<Product>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Product>> e) throws Exception {
                e.onNext(db.getListItemsFromBasket());
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<Integer> addListItemsToBasket(final List<Product> listItems) {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(db.addListItemsToBasket(listItems));
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> addIngredientToProduct(final int productId, final Ingredient ingredient) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> e) throws Exception {
                e.onNext(db.addIngredientToProduct(productId, ingredient));
                e.onComplete();
            }
        });
    }
}
