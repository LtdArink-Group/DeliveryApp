package ru.arink_group.deliveryapp.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.Category;

/**
 * Created by kirillvs on 03.10.17.
 */

public interface CategoriesRepository {
    Observable<List<Category>> categoriesList();
//    Observable<Category> category();
}
