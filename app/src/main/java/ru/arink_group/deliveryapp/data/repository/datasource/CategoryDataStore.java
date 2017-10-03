package ru.arink_group.deliveryapp.data.repository.datasource;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.Category;

/**
 * Created by kirillvs on 03.10.17.
 */

public interface CategoryDataStore {

    Observable<List<Category>> categoriesDataList();

    Observable<Category> categoryData(int categoryId);
}
