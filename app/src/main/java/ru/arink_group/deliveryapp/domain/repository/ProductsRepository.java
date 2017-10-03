package ru.arink_group.deliveryapp.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.Product;

/**
 * Created by kirillvs on 03.10.17.
 */

public interface ProductsRepository {

    Observable<List<Product>> productsList(int categoryId);

    Observable<Product> product(int productId);

}
