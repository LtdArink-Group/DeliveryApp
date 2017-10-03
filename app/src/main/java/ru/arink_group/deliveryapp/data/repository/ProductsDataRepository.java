package ru.arink_group.deliveryapp.data.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.data.repository.datasource.ProductsDataStoreFactory;
import ru.arink_group.deliveryapp.domain.Product;
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
    public Observable<List<Product>> productsList(int categoryId) {
        return storeFactory.create().productsDataList(categoryId);
    }

    @Override
    public Observable<Product> product(int productId) {
        return storeFactory.createProduct(productId).productData(productId);
    }
}
