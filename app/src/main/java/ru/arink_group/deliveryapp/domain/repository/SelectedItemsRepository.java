package ru.arink_group.deliveryapp.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.Product;

/**
 * Created by kirillvs on 09.10.17.
 */

public interface SelectedItemsRepository {

    Observable<Boolean> addItemToBasket(Product selectedProduct);

    Observable<Boolean> removeItemFromBasket(int selectedProductId);

    Observable<List<Product>> getListItemsFromBasket();
}
