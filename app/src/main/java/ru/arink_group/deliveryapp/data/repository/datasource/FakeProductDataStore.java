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

        Ingredient[] ingredients1 = new Ingredient[] {new Ingredient(), new Ingredient(), new Ingredient(), new Ingredient()};
        ingredients1[0].setName("Соус");
        ingredients1[0].setDescription("Вкуснейший соус");
        ingredients1[0].setPrice(100.0);
        ingredients1[1].setName("Кетчуп");
        ingredients1[1].setDescription("Вкуснейший кетчуп");
        ingredients1[1].setPrice(250.0);
        ingredients1[2].setName("Подливка");
        ingredients1[2].setDescription("Так себе");
        ingredients1[2].setPrice(30.0);
        ingredients1[3].setName("Зелень");
        ingredients1[3].setDescription("Сойдет");
        ingredients1[3].setPrice(50.0);

        Ingredient[] ingredients2 = new Ingredient[] {new Ingredient(), new Ingredient(), new Ingredient(), new Ingredient()};
        ingredients2[0].setName("Соус");
        ingredients2[0].setDescription("Вкуснейший соус");
        ingredients2[0].setPrice(100.0);
        ingredients2[1].setName("Кетчуп");
        ingredients2[1].setDescription("Вкуснейший кетчуп");
        ingredients2[1].setPrice(250.0);
        ingredients2[2].setName("Подливка");
        ingredients2[2].setDescription("Так себе");
        ingredients2[2].setPrice(30.0);
        ingredients2[3].setName("Зелень");
        ingredients2[3].setDescription("Сойдет");
        ingredients2[3].setPrice(50.0);

        Ingredient[] ingredients3 = new Ingredient[] {new Ingredient(), new Ingredient(), new Ingredient(), new Ingredient()};
        ingredients3[0].setName("Соус");
        ingredients3[0].setDescription("Вкуснейший соус");
        ingredients3[0].setPrice(100.0);
        ingredients3[1].setName("Кетчуп");
        ingredients3[1].setDescription("Вкуснейший кетчуп");
        ingredients3[1].setPrice(250.0);
        ingredients3[2].setName("Подливка");
        ingredients3[2].setDescription("Так себе");
        ingredients3[2].setPrice(30.0);
        ingredients3[3].setName("Зелень");
        ingredients3[3].setDescription("Сойдет");
        ingredients3[3].setPrice(50.0);

        Ingredient[] ingredients4 = new Ingredient[] {new Ingredient(), new Ingredient(), new Ingredient(), new Ingredient()};
        ingredients4[0].setName("Соус");
        ingredients4[0].setDescription("Вкуснейший соус");
        ingredients4[0].setPrice(100.0);
        ingredients4[1].setName("Кетчуп");
        ingredients4[1].setDescription("Вкуснейший кетчуп");
        ingredients4[1].setPrice(250.0);
        ingredients4[2].setName("Подливка");
        ingredients4[2].setDescription("Так себе");
        ingredients4[2].setPrice(30.0);
        ingredients4[3].setName("Зелень");
        ingredients4[3].setDescription("Сойдет");
        ingredients4[3].setPrice(50.0);

        one.setIngredients(ingredients1);
        two.setIngredients(ingredients2);
        three.setIngredients(ingredients3);
        five.setIngredients(ingredients4);

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
