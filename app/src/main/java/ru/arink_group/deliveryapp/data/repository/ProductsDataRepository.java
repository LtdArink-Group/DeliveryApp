package ru.arink_group.deliveryapp.data.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.data.repository.factory.ProductsDataStoreFactory;
import ru.arink_group.deliveryapp.domain.dto.ProductDTO;
import ru.arink_group.deliveryapp.domain.repository.ProductsRepository;

/**
 * Created by kirillvs on 03.10.17.
 */

public class ProductsDataRepository implements ProductsRepository {

    private static ProductsDataRepository instance;
    private ProductsDataStoreFactory storeFactory;

    private ProductsDataRepository() {
        this.storeFactory = ProductsDataStoreFactory.singletonProductsDataStoreFactory();
    }

    public static ProductsDataRepository singletonProductsDataRepository() {
        if(instance == null) {
            instance = new ProductsDataRepository();
        }

        return instance;
    }


    @Override
    public Observable<List<ProductDTO>> productsList(int categoryId) {
        return storeFactory.create().productsDataList(categoryId);
    }

    @Override
    public Observable<ProductDTO> product(int productId) {
        return storeFactory.createProduct(productId).productData(productId);
    }
}
