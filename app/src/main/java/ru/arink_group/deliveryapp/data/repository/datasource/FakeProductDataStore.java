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

        String[] size = new String[] {"100", "200", "300"};

        Product one = new Product();
        Product two = new Product();
        Product three = new Product();
        Product five = new Product();

        one.setName("Lorem");
        two.setName("Lorem");
        three.setName("Lorem");
        five.setName("Lorem");

        one.setDescription("Lorem ipsum dolor sit amet, omnium ceteros nam ut. Odio nullam ut mei. Mea probo possim et, affert legimus erroribus ne sit, mundi vidisse malorum vix ut. Vis alienum omnesque platonem te. Oblique corpora accumsan id per, pri laudem luptatum no, ius eu virtute laoreet verterem.");
        two.setDescription("Lorem ipsum dolor sit amet, omnium ceteros nam ut. Odio nullam ut mei. Mea probo possim et, affert legimus erroribus ne sit, mundi vidisse malorum vix ut. Vis alienum omnesque platonem te. Oblique corpora accumsan id per, pri laudem luptatum no, ius eu virtute laoreet verterem.");
        three.setDescription("Lorem ipsum dolor sit amet, omnium ceteros nam ut. Odio nullam ut mei. Mea probo possim et, affert legimus erroribus ne sit, mundi vidisse malorum vix ut. Vis alienum omnesque platonem te. Oblique corpora accumsan id per, pri laudem luptatum no, ius eu virtute laoreet verterem.");
        five.setDescription("Lorem ipsum dolor sit amet, omnium ceteros nam ut. Odio nullam ut mei. Mea probo possim et, affert legimus erroribus ne sit, mundi vidisse malorum vix ut. Vis alienum omnesque platonem te. Oblique corpora accumsan id per, pri laudem luptatum no, ius eu virtute laoreet verterem.");

        one.setPrice("100500");
        two.setPrice("100500");
        three.setPrice("100500");
        five.setPrice("100500");

        one.setUnit("гр.");
        two.setUnit("гр.");
        three.setUnit("гр.");
        five.setUnit("гр.");

        one.setSize(size);
        two.setSize(size);
        three.setSize(size);
        five.setSize(size);

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
