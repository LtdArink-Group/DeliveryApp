package ru.arink_group.deliveryapp.presentation.di.module;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.arink_group.deliveryapp.domain.interactors.GetCategoriesList;
import ru.arink_group.deliveryapp.domain.interactors.GetProduct;
import ru.arink_group.deliveryapp.domain.interactors.GetProductsList;

/**
 * Created by kirillvs on 06.10.17.
 */

@Module
public class InteractorsModule {

    @Provides
    @NonNull
    public GetCategoriesList provideCategoriesListInteractor() {
        return new GetCategoriesList();
    }

    @Provides
    @NonNull
    public GetProductsList provideProductsListInteractor() {
        return new GetProductsList();
    }

    @Provides
    @NonNull
    public GetProduct provideProduct() {
        return new GetProduct();
    }

}
