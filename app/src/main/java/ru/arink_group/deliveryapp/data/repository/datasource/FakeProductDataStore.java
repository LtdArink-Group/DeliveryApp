package ru.arink_group.deliveryapp.data.repository.datasource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.Category;
import ru.arink_group.deliveryapp.domain.Product;

/**
 * Created by kirillvs on 03.10.17.
 */

public class FakeProductDataStore implements ProductDataStore {

    private static List<Product> products() {
        List<Product> productsList = new ArrayList<>();

        Product one = new Product();
        Product two = new Product();
        Product three = new Product();
        Product five = new Product();

        one.setName("one");
        two.setName("two");
        three.setName("three");
        five.setName("five");

        one.setId(1);
        two.setId(2);
        three.setId(3);
        five.setId(5);

        productsList.add(one);
        productsList.add(two);
        productsList.add(three);
        productsList.add(five);

        return productsList;
    }


    @Override
    public Observable<List<Product>> productsDataList(int categoryId) {

        return Observable.just(products());

    }

    @Override
    public Observable<Product> productData(int productId) {
        Product result = null;
        for(Product product : products()) {
            if (product.getId() == productId) result = product;
        }
        return Observable.just(result);
    }
}
