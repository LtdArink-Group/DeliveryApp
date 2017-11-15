package ru.arink_group.deliveryapp.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.dao.Product;
import ru.arink_group.deliveryapp.domain.repository.SelectedItemsRepository;
import ru.arink_group.deliveryapp.App;

/**
 * Created by kirillvs on 09.10.17.
 */

public class GetListItemsFromBasket extends UseCase<List<Product>, GetListItemsFromBasket.Params> {

    @Inject public SelectedItemsRepository selectedItemsRepository;

    public GetListItemsFromBasket() {
        super();
        App.getComponent().inject(this);
    }

    @Override
    Observable<List<Product>> buildUseCaseObservable(Params params) {
        return selectedItemsRepository.getListItemsFromBasket();
    }

    public static final class Params {

    }

}
