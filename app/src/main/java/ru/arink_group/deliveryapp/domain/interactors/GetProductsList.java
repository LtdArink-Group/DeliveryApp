package ru.arink_group.deliveryapp.domain.interactors;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.Product;

/**
 * Created by kirillvs on 03.10.17.
 */

public class GetProductsList extends UseCase<List<Product>, Void> {

    public GetProductsList() {
        super();
    }

    @Override
    Observable<List<Product>> buildUseCaseObservable(Void aVoid) {
        return null;
    }
}
