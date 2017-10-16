package ru.arink_group.deliveryapp.data.repository.datasource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.Category;

/**
 * Created by kirillvs on 03.10.17.
 */

public class FakeCategoryDataStore implements CategoryDataStore {

    @Override
    public Observable<List<Category>> categoriesDataList() {

        List<Category> categoriestList = new ArrayList<>();
        Category one = new Category();
        Category two = new Category();
        Category three = new Category();
        Category five = new Category();

        one.setName("Пицца");
        two.setName("Напитки");
        three.setName("Гамбургеры");
        five.setName("Суши");

        one.setIcon("pizza");
        two.setIcon("drink");
        three.setIcon("hamburger");

        one.setId(1);
        two.setId(2);
        three.setId(3);
        five.setId(4);

        categoriestList.add(one);
        categoriestList.add(two);
        categoriestList.add(three);
        categoriestList.add(five);

        return Observable.just(categoriestList);

    }

    @Override
    public Observable<Category> categoryData(int categoryId) {
        return null;
    }
}
