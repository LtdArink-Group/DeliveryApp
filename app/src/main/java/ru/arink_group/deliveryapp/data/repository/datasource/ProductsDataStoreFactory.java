package ru.arink_group.deliveryapp.data.repository.datasource;

/**
 * Created by kirillvs on 03.10.17.
 */

public class ProductsDataStoreFactory {

    public static ProductsDataStoreFactory instance;

    private ProductsDataStoreFactory() {

    }

    public static ProductsDataStoreFactory singletonProductsDataStoreFactory() {
        if(instance == null) {
            instance = new ProductsDataStoreFactory();
        }

        return instance;
    }

    public ProductDataStore create() {
        return null;
    }

    public ProductDataStore createProduct(int productId) {
        return create();
    }
}
