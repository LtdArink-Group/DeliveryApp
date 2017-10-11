package ru.arink_group.deliveryapp.domain.interactors;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.SelectedProduct;
import ru.arink_group.deliveryapp.domain.repository.SelectedItemsRepository;
import ru.arink_group.deliveryapp.presentation.App;

/**
 * Created by kirillvs on 09.10.17.
 */

public class AddItemToBasket extends UseCase<SelectedProduct, AddItemToBasket.Params> {

    @Inject
    public SelectedItemsRepository selectedItemsRepository;

    public AddItemToBasket() {
        super();
        App.getComponent().inject(this);
    }

    @Override
    Observable<SelectedProduct> buildUseCaseObservable(Params params) {
        return selectedItemsRepository.addItemToBasket(params.selectedProduct);
    }

    public static final class Params {

        private SelectedProduct selectedProduct;

        private Params(SelectedProduct sp) {
            this.selectedProduct = sp;
        }

        public Params forBasketAddItem(SelectedProduct sp) {
            return new Params(sp);
        }

    }
}
