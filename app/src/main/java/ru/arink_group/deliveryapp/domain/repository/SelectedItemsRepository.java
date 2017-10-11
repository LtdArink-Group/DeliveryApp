package ru.arink_group.deliveryapp.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.SelectedProduct;

/**
 * Created by kirillvs on 09.10.17.
 */

public interface SelectedItemsRepository {

    Observable<SelectedProduct> addItemToBasket(SelectedProduct selectedProduct);

    Observable<SelectedProduct> removeItemFromBasket(int selectedProductId);

    Observable<List<SelectedProduct>> getListItemsFromBasket();
}
