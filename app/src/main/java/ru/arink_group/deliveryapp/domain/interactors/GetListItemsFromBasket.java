package ru.arink_group.deliveryapp.domain.interactors;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.SelectedProduct;
import ru.arink_group.deliveryapp.domain.repository.SelectedItemsRepository;
import ru.arink_group.deliveryapp.presentation.App;

/**
 * Created by kirillvs on 09.10.17.
 */

public class GetListItemsFromBasket extends UseCase<SelectedProduct[], GetListItemsFromBasket.Params> {

    @Inject public SelectedItemsRepository selectedItemsRepository;

    public GetListItemsFromBasket() {
        super();
        App.getComponent().inject(this);
    }

    @Override
    Observable<SelectedProduct[]> buildUseCaseObservable(Params params) {
        return null;
    }

    public static final class Params {

    }

}
