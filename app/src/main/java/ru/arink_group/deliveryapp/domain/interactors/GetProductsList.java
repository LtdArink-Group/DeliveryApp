package ru.arink_group.deliveryapp.domain.interactors;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import ru.arink_group.deliveryapp.data.repository.ProductsDataRepository;
import ru.arink_group.deliveryapp.domain.dao.Product;
import ru.arink_group.deliveryapp.domain.dto.ProductDTO;
import ru.arink_group.deliveryapp.domain.dto.TransformerDTO;
import ru.arink_group.deliveryapp.domain.repository.ProductsRepository;

/**
 * Created by kirillvs on 03.10.17.
 */

public class GetProductsList extends UseCase<List<Product>, GetProductsList.Params> {

    private ProductsRepository productsRepository;

    public GetProductsList() {
        super();
        productsRepository = ProductsDataRepository.singletonProductsDataRepository();
    }

    @Override
    Observable<List<Product>> buildUseCaseObservable(Params params) {
        return productsRepository.productsList(params.categoryId).map(new Function<List<ProductDTO>, List<Product>>() {
            @Override
            public List<Product> apply(@NonNull List<ProductDTO> productDTOs) throws Exception {
                return TransformerDTO.transformProducts(productDTOs);
            }
        });
    }

    public static final class Params {

        private final int categoryId;

        private Params(int categoryId) {
            this.categoryId = categoryId;
        }

        public static Params forProductsList(int categoryId) {
            return new Params(categoryId);
        }
    }

}
