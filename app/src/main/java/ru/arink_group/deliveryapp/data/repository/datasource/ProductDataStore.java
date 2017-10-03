package ru.arink_group.deliveryapp.data.repository.datasource;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.Category;
import ru.arink_group.deliveryapp.domain.Product;

/**
 * Created by kirillvs on 03.10.17.
 */

public interface ProductDataStore {

    Observable<List<Product>> productsDataList();

    Observable<Product> productData(int productId);
}
