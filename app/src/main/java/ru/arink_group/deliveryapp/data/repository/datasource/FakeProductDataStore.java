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
        // TODO BIG REWORK

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

        Portion[] portions1 = new Portion[] {new Portion(), new Portion(), new Portion()};
        portions1[0].setName("Пицель");
        portions1[0].setPrice(250.0);
        portions1[1].setName("Шницель");
        portions1[1].setPrice(500.0);
        portions1[2].setName("Водка");
        portions1[2].setPrice(700.0);
        Portion[] portions2 = new Portion[] {new Portion(), new Portion(), new Portion()};
        portions2[0].setName("Пицель");
        portions2[0].setPrice(250.0);
        portions2[1].setName("Шницель");
        portions2[1].setPrice(500.0);
        portions2[2].setName("Водка");
        portions2[2].setPrice(700.0);
        Portion[] portions3 = new Portion[] {new Portion(), new Portion(), new Portion()};
        portions3[0].setName("Пицель");
        portions3[0].setPrice(250.0);
        portions3[1].setName("Шницель");
        portions3[1].setPrice(500.0);
        portions3[2].setName("Водка");
        portions3[2].setPrice(700.0);
        Portion[] portions4 = new Portion[] {new Portion(), new Portion(), new Portion()};
        portions4[0].setName("Пицель");
        portions4[0].setPrice(250.0);
        portions4[1].setName("Шницель");
        portions4[1].setPrice(500.0);
        portions4[2].setName("Водка");
        portions4[2].setPrice(700.0);

        one.setPortions(portions1);
        two.setPortions(portions2);
        three.setPortions(portions3);
        five.setPortions(portions4);


        one.setId(1);
        two.setId(2);
        three.setId(3);
        five.setId(5);

        Ingredient[] ingredients = new Ingredient[] {new Ingredient(), new Ingredient(), new Ingredient(), new Ingredient()};

        ingredients[0].setName("Соус");
        ingredients[0].setDescription("Вкуснейший соус");
        ingredients[0].setPrice(100.0);

        ingredients[1].setName("Кетчуп");
        ingredients[1].setDescription("Вкуснейший кетчуп");
        ingredients[1].setPrice(250.0);

        ingredients[2].setName("Подливка");
        ingredients[2].setDescription("Так себе");
        ingredients[2].setPrice(30.0);

        ingredients[3].setName("Зелень");
        ingredients[3].setDescription("Сойдет");
        ingredients[3].setPrice(50.0);

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
