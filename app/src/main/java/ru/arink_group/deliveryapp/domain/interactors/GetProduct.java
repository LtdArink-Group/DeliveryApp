package ru.arink_group.deliveryapp.domain.interactors;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.data.repository.ProductsDataRepository;
import ru.arink_group.deliveryapp.domain.Product;
import ru.arink_group.deliveryapp.domain.repository.ProductsRepository;

/**
 * Created by kirillvs on 03.10.17.
 */

public class GetProduct extends UseCase<Product, GetProduct.Params> {

    ProductsRepository productsRepository;

    public GetProduct() {
        super();
        productsRepository = ProductsDataRepository.singletonProductsDataRepository();
    }

    @Override
    Observable<Product> buildUseCaseObservable(Params params) {
        return productsRepository.product(params.productId);
    }

    public static final class Params {

        private final int productId;

        private Params(int productId) {
            this.productId = productId;
        }

        public static Params forProduct(int productId) {
            return new Params(productId);
        }
    }

}
