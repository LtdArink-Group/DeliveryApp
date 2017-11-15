package ru.arink_group.deliveryapp.domain.interactors;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.dao.Product;
import ru.arink_group.deliveryapp.domain.repository.SelectedItemsRepository;
import ru.arink_group.deliveryapp.App;

/**
 * Created by kirillvs on 09.10.17.
 */

public class AddItemToBasket extends UseCase<Boolean, AddItemToBasket.Params> {

    @Inject
    public SelectedItemsRepository selectedItemsRepository;

    public AddItemToBasket() {
        super();
        App.getComponent().inject(this);
    }

    @Override
    Observable<Boolean> buildUseCaseObservable(Params params) {
        return selectedItemsRepository.addItemToBasket(params.selectedProduct);
    }

    public static final class Params {

        private Product selectedProduct;

        private Params(Product sp) {
            this.selectedProduct = sp;
        }

        public static Params forBasketAddItem(Product sp) {
            return new Params(sp);
        }

    }
}
