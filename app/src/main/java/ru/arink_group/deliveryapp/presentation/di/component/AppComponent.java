package ru.arink_group.deliveryapp.presentation.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.arink_group.deliveryapp.data.db.ProductsDbHelper;
import ru.arink_group.deliveryapp.data.repository.AccountDataRepository;
import ru.arink_group.deliveryapp.data.repository.CompanyDataRepository;
import ru.arink_group.deliveryapp.data.repository.OrderDataRepository;
import ru.arink_group.deliveryapp.data.repository.SelectedItemsDataRepository;
import ru.arink_group.deliveryapp.data.repository.factory.AccountDataFactory;
import ru.arink_group.deliveryapp.data.repository.factory.CompanyDataFactory;
import ru.arink_group.deliveryapp.data.repository.factory.SelectedItemsDataFactory;
import ru.arink_group.deliveryapp.domain.interactors.AddAddress;
import ru.arink_group.deliveryapp.domain.interactors.AddIngredientToBasket;
import ru.arink_group.deliveryapp.domain.interactors.AddItemToBasket;
import ru.arink_group.deliveryapp.domain.interactors.AddItemToBasketOrNull;
import ru.arink_group.deliveryapp.domain.interactors.AddListItemsToBasket;
import ru.arink_group.deliveryapp.domain.interactors.CancelOrder;
import ru.arink_group.deliveryapp.domain.interactors.ClearItemsFromBasket;
import ru.arink_group.deliveryapp.domain.interactors.CreateAccount;
import ru.arink_group.deliveryapp.domain.interactors.DeleteAddress;
import ru.arink_group.deliveryapp.domain.interactors.GetAccount;
import ru.arink_group.deliveryapp.domain.interactors.GetCompany;
import ru.arink_group.deliveryapp.domain.interactors.GetListItemsFromBasket;
import ru.arink_group.deliveryapp.domain.interactors.GetOrders;
import ru.arink_group.deliveryapp.domain.interactors.RemoveItemFromBasket;
import ru.arink_group.deliveryapp.domain.interactors.SendOrderToServer;
import ru.arink_group.deliveryapp.domain.interactors.UpdateAccount;
import ru.arink_group.deliveryapp.domain.interactors.UpdateAddress;
import ru.arink_group.deliveryapp.domain.interactors.UpdateAddressPatch;
import ru.arink_group.deliveryapp.presentation.di.module.AppModule;
import ru.arink_group.deliveryapp.presentation.di.module.FactoriesModule;
import ru.arink_group.deliveryapp.presentation.di.module.InteractorsModule;
import ru.arink_group.deliveryapp.presentation.di.module.RepositoriesModule;
import ru.arink_group.deliveryapp.presentation.di.module.StoreModule;
import ru.arink_group.deliveryapp.presentation.presenter.AccountPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.AddressPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.CategoriesPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.IngredientsPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.OrderPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.OrdersHistoryPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.ProductsPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.RetryOrderPresenterImpl;

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
    void inject(AccountDataFactory accountDataFactory);
    void inject(AccountDataRepository accountDataRepository);
    void inject(CreateAccount createAccount);
    void inject(UpdateAccount updateAccount);
    void inject(AddAddress addAddress);
    void inject(UpdateAddress updateAddress);
    void inject(DeleteAddress deleteAddress);
    void inject(GetAccount getAccount);
    void inject(AccountPresenterImpl accountPresenter);
    void inject(UpdateAddressPatch updateAddressPatch);
    void inject(AddressPresenterImpl addressPresenter);
    void inject(CompanyDataRepository  companyDataRepository);
    void inject(CompanyDataFactory companyDataFactory);
    void inject(GetCompany getCompany);
    void inject(OrderDataRepository orderDataRepository);
    void inject(SendOrderToServer sendOrderToServer);
    void inject(GetOrders getOrders);
    void inject(OrdersHistoryPresenterImpl ordersHistoryPresenter);
    void inject(RetryOrderPresenterImpl retryOrderPresenter);
    void inject(CancelOrder cancelOrder);
}
