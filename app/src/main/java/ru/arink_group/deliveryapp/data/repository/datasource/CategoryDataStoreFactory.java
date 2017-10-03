package ru.arink_group.deliveryapp.data.repository.datasource;

/**
 * Created by kirillvs on 03.10.17.
 */

public class CategoryDataStoreFactory {

    public static CategoryDataStoreFactory instance;

    private CategoryDataStoreFactory() {

    }

    public static CategoryDataStoreFactory singletonCategoryDataStoreFactory() {
        if(instance == null) {
            instance = new CategoryDataStoreFactory();
        }

        return instance;
    }

    public CategoryDataStore create() {
        return new FakeCategoryDataStore();
    }

    public CategoryDataStore createCategory(int categoryId) {
        return create();
    }
}
