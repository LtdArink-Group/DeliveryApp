package ru.arink_group.deliveryapp.domain.interactors;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.repository.SelectedItemsRepository;
import ru.arink_group.deliveryapp.App;

/**
 * Created by kirillvs on 09.10.17.
 */

public class RemoveItemFromBasket extends UseCase<Boolean, RemoveItemFromBasket.Params> {

    @Inject
    public SelectedItemsRepository selectedItemsRepository;

    public RemoveItemFromBasket() {
        super();
        App.getComponent().inject(this);
    }

    @Override
    Observable<Boolean> buildUseCaseObservable(Params params) {
        return selectedItemsRepository.removeItemFromBasket(params.selectedProductId);
    }

    public static final class Params {

        private int selectedProductId;

        private Params(int id) {
            selectedProductId = id;
        }

        public Params forBasketRemoveItem(int id) {
            return new Params(id);
        }


    }

}
