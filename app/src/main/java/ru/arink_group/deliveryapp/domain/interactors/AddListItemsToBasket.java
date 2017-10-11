package ru.arink_group.deliveryapp.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.SelectedProduct;
import ru.arink_group.deliveryapp.domain.repository.SelectedItemsRepository;
import ru.arink_group.deliveryapp.presentation.App;

/**
 * Created by kirillvs on 11.10.17.
 */

public class AddListItemsToBasket extends UseCase<Integer, AddListItemsToBasket.Params> {

    @Inject
    public SelectedItemsRepository selectedItemsRepository;

    public AddListItemsToBasket() {
        super();
        App.getComponent().inject(this);
    }

    @Override
    Observable<Integer> buildUseCaseObservable(Params params) {
        return null;
    }

    public static final class Params {
        private List<SelectedProduct> selectedProductList;

        private Params(List<SelectedProduct> sps) {
            this.selectedProductList = sps;
        }

        public static Params forBasketAddItemsList(List<SelectedProduct> sps) {
            return new Params(sps);
        }
    }
}
