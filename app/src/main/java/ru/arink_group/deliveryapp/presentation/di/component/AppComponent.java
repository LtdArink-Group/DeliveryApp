package ru.arink_group.deliveryapp.presentation.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.arink_group.deliveryapp.data.db.Db;
import ru.arink_group.deliveryapp.data.db.DeliveryAppDatabaseHelper;
import ru.arink_group.deliveryapp.data.repository.SelectedItemsDataRepository;
import ru.arink_group.deliveryapp.data.repository.datasource.SelectedItemsDataFactory;
import ru.arink_group.deliveryapp.domain.interactors.AddItemToBasket;
import ru.arink_group.deliveryapp.domain.interactors.AddListItemsToBasket;
import ru.arink_group.deliveryapp.domain.interactors.GetListItemsFromBasket;
import ru.arink_group.deliveryapp.domain.interactors.RemoveItemFromBasket;
import ru.arink_group.deliveryapp.presentation.di.module.AppModule;
import ru.arink_group.deliveryapp.presentation.di.module.FactoriesModule;
import ru.arink_group.deliveryapp.presentation.di.module.InteractorsModule;
import ru.arink_group.deliveryapp.presentation.di.module.RepositoriesModule;
import ru.arink_group.deliveryapp.presentation.di.module.StoreModule;
import ru.arink_group.deliveryapp.presentation.presenter.CartePresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.ProductsPresenterImpl;

/**
 * Created by kirillvs on 06.10.17.
 */


@Component(modules = {AppModule.class, InteractorsModule.class, FactoriesModule.class, RepositoriesModule.class, StoreModule.class})
@Singleton
public interface AppComponent {
    void inject(CartePresenterImpl carte);
    void inject(ProductsPresenterImpl products);
    void inject(RemoveItemFromBasket removeItemFromBasket);
    void inject(GetListItemsFromBasket getListItemsFromBasket);
    void inject(AddItemToBasket addItemToBasket);
    void inject(SelectedItemsDataRepository selectedItemsDataRepository);
    void inject(SelectedItemsDataFactory selectedItemsDataFactory);
    void inject(Db db);
    void inject(AddListItemsToBasket addListItemsToBasket);
}
