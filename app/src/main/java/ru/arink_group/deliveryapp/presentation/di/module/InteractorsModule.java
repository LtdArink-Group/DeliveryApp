package ru.arink_group.deliveryapp.presentation.di.module;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.arink_group.deliveryapp.domain.interactors.GetCategoriesList;

/**
 * Created by kirillvs on 06.10.17.
 */

@Module
public class InteractorsModule {

    @Provides
    @NonNull
    @Singleton
    public GetCategoriesList provideCategoriesListInteractor() {
        return new GetCategoriesList();
    }

}
