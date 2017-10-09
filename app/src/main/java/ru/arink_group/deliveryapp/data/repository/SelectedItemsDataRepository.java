package ru.arink_group.deliveryapp.data.repository;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.data.repository.datasource.SelectedItemsDataFactory;
import ru.arink_group.deliveryapp.domain.SelectedProduct;
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
    public Observable<SelectedProduct> addItemToBasket(SelectedProduct selectedProduct) {
        return factory.create().addItemToBasket(selectedProduct);
    }

    @Override
    public Observable<SelectedProduct> removeItemFromBasket(int selectedProductId) {
        return factory.create().removeItemFromBasket(selectedProductId);
    }

    @Override
    public Observable<SelectedProduct[]> getListItemsFromBasket() {
        return factory.create().getListItemsFromBasket();
    }
}
