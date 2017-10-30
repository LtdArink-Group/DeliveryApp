package ru.arink_group.deliveryapp.presentation.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.arink_group.deliveryapp.data.db.ProductsDbHelper;
import ru.arink_group.deliveryapp.data.repository.SelectedItemsDataRepository;
import ru.arink_group.deliveryapp.data.repository.factory.SelectedItemsDataFactory;
import ru.arink_group.deliveryapp.domain.interactors.AddIngredientToBasket;
import ru.arink_group.deliveryapp.domain.interactors.AddItemToBasket;
import ru.arink_group.deliveryapp.domain.interactors.AddItemToBasketOrNull;
import ru.arink_group.deliveryapp.domain.interactors.AddListItemsToBasket;
import ru.arink_group.deliveryapp.domain.interactors.ClearItemsFromBasket;
import ru.arink_group.deliveryapp.domain.interactors.GetListItemsFromBasket;
import ru.arink_group.deliveryapp.domain.interactors.RemoveItemFromBasket;
import ru.arink_group.deliveryapp.presentation.di.module.AppModule;
import ru.arink_group.deliveryapp.presentation.di.module.FactoriesModule;
import ru.arink_group.deliveryapp.presentation.di.module.InteractorsModule;
import ru.arink_group.deliveryapp.presentation.di.module.RepositoriesModule;
import ru.arink_group.deliveryapp.presentation.di.module.StoreModule;
import ru.arink_group.deliveryapp.presentation.presenter.CategoriesPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.IngredientsPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.OrderPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.ProductsPresenterImpl;

/**
 * Created by kirillvs on 06.10.17.
 */


@Component(modules = {AppModule.class, InteractorsModule.class, FactoriesModule.class, RepositoriesModule.class, StoreModule.class})
@Singleton
public interface AppComponent {
    void inject(CategoriesPresenterImpl carte);
    void inject(ProductsPresenterImpl products);
    void inject(RemoveItemFromBasket removeItemFromBasket);
    void inject(GetListItemsFromBasket getListItemsFromBasket);
    void inject(AddItemToBasket addItemToBasket);
    void inject(SelectedItemsDataRepository selectedItemsDataRepository);
    void inject(SelectedItemsDataFactory selectedItemsDataFactory);
    void inject(ProductsDbHelper productsDbHelper);
    void inject(AddListItemsToBasket addListItemsToBasket);
    void inject(IngredientsPresenterImpl ingredientsPresenter);
    void inject(AddIngredientToBasket addIngredientToBasket);
    void inject(OrderPresenterImpl orderPresenter);
    void inject(AddItemToBasketOrNull addItemToBasketOrNull);
    void inject(ClearItemsFromBasket clearItemsFromBasket);
}
