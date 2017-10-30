package ru.arink_group.deliveryapp.data.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.data.repository.datasource.CategoryDataStore;
import ru.arink_group.deliveryapp.data.repository.factory.CategoryDataStoreFactory;
import ru.arink_group.deliveryapp.domain.dto.CategoryDTO;

/**
 * Created by kirillvs on 03.10.17.
 */

public class CategoriesDataRepository implements ru.arink_group.deliveryapp.domain.repository.CategoriesRepository{

    private static CategoriesDataRepository instance;
    private CategoryDataStoreFactory categoryDataStoreFactory;

    private CategoriesDataRepository() {
        categoryDataStoreFactory = CategoryDataStoreFactory.singletonCategoryDataStoreFactory();
    }

    public static CategoriesDataRepository singletonCategoriesRepository() {
        if(instance == null) {
            instance = new CategoriesDataRepository();
        }

        return instance;
    }

    @Override
    public Observable<List<CategoryDTO>> categoriesList() {
        CategoryDataStore dataStore = categoryDataStoreFactory.create();
        return dataStore.categoriesDataList();
    }
}
