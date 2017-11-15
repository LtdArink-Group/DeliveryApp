package ru.arink_group.deliveryapp.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.dao.Product;
import ru.arink_group.deliveryapp.domain.repository.SelectedItemsRepository;
import ru.arink_group.deliveryapp.App;

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
        private List<Product> selectedProductList;

        private Params(List<Product> sps) {
            this.selectedProductList = sps;
        }

        public static Params forBasketAddItemsList(List<Product> sps) {
            return new Params(sps);
        }
    }
}
