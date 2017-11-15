package ru.arink_group.deliveryapp.data.fake;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.data.repository.datasource.CategoryDataStore;
import ru.arink_group.deliveryapp.domain.dto.CategoryDTO;

/**
 * Created by kirillvs on 03.10.17.
 */

public class FakeCategoryDataStore implements CategoryDataStore {

    @Override
    public Observable<List<CategoryDTO>> categoriesDataList() {

        List<CategoryDTO> categoriestList = new ArrayList<>();
        CategoryDTO one = new CategoryDTO();
        CategoryDTO two = new CategoryDTO();
        CategoryDTO three = new CategoryDTO();
        CategoryDTO five = new CategoryDTO();

        one.setName("Пицца");
        two.setName("Напитки");
        three.setName("Гамбургеры");
        five.setName("Суши");

        one.setIconType("icon_pizza");
        two.setIconType("icon_drinks");
        three.setIconType("icon_burger");

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

}
