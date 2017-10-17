package ru.arink_group.deliveryapp.presentation.di.module;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.arink_group.deliveryapp.domain.interactors.AddIngredientToBasket;
import ru.arink_group.deliveryapp.domain.interactors.AddItemToBasket;
import ru.arink_group.deliveryapp.domain.interactors.AddListItemsToBasket;
import ru.arink_group.deliveryapp.domain.interactors.GetCategoriesList;
import ru.arink_group.deliveryapp.domain.interactors.GetListItemsFromBasket;
import ru.arink_group.deliveryapp.domain.interactors.GetProduct;
import ru.arink_group.deliveryapp.domain.interactors.GetProductsList;
import ru.arink_group.deliveryapp.domain.interactors.RemoveItemFromBasket;

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

    @Provides
    @NonNull
    public AddItemToBasket provideAddItemToBasket() {
        return new AddItemToBasket();
    }

    @Provides
    @NonNull
    public RemoveItemFromBasket provideRemoveItemFromBusket() {
        return new RemoveItemFromBasket();
    }

    @Provides
    @NonNull
    public GetListItemsFromBasket provideGetListItemsFromBasket() {
        return new GetListItemsFromBasket();
    }

    @Provides
    @NonNull
    public AddListItemsToBasket provideAddListItemsToBasket() {
        return new AddListItemsToBasket();
    }

    @Provides
    @NonNull
    public AddIngredientToBasket provideAddIngredientToBasket() {
        return new AddIngredientToBasket();
    }

}
