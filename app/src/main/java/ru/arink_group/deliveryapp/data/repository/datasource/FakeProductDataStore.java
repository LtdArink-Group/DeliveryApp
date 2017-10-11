package ru.arink_group.deliveryapp.data.repository.datasource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.Category;
import ru.arink_group.deliveryapp.domain.Ingredient;
import ru.arink_group.deliveryapp.domain.Portion;
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

        Portion[] portions = new Portion[] {new Portion(), new Portion(), new Portion()};
        portions[0].setName("Пицель");
        portions[0].setPrice("250");
        portions[1].setName("Шницель");
        portions[1].setPrice("500");
        portions[2].setName("Водка");
        portions[2].setPrice("700");

        one.setPortions(portions);
        two.setPortions(portions);
        three.setPortions(portions);
        five.setPortions(portions);

        one.setSize(size);
        two.setSize(size);
        three.setSize(size);
        five.setSize(size);

        one.setId(1);
        two.setId(2);
        three.setId(3);
        five.setId(5);

        Ingredient[] ingredients = new Ingredient[] {new Ingredient(), new Ingredient(), new Ingredient(), new Ingredient()};

        ingredients[0].setName("Соус");
        ingredients[0].setDescription("Вкуснейший соус");
        ingredients[0].setSize("50");
        ingredients[0].setPrice("100");

        ingredients[1].setName("Кетчуп");
        ingredients[1].setDescription("Вкуснейший кетчуп");
        ingredients[1].setSize("50");
        ingredients[1].setPrice("250");

        ingredients[2].setName("Подливка");
        ingredients[2].setDescription("Так себе");
        ingredients[2].setSize("50");
        ingredients[2].setPrice("30");

        ingredients[3].setName("Зелень");
        ingredients[3].setDescription("Сойдет");
        ingredients[3].setSize("50");
        ingredients[3].setPrice("50");

        one.setIngredients(ingredients);
        two.setIngredients(ingredients);
        three.setIngredients(ingredients);
        five.setIngredients(ingredients);

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
