package ru.arink_group.deliveryapp.presentation.di.module;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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
import ru.arink_group.deliveryapp.domain.interactors.GetCategoriesList;
import ru.arink_group.deliveryapp.domain.interactors.GetListItemsFromBasket;
import ru.arink_group.deliveryapp.domain.interactors.GetOrders;
import ru.arink_group.deliveryapp.domain.interactors.GetProduct;
import ru.arink_group.deliveryapp.domain.interactors.GetProductsList;
import ru.arink_group.deliveryapp.domain.interactors.RemoveItemFromBasket;
import ru.arink_group.deliveryapp.domain.interactors.SendOrderToServer;
import ru.arink_group.deliveryapp.domain.interactors.UpdateAccount;
import ru.arink_group.deliveryapp.domain.interactors.UpdateAddress;
import ru.arink_group.deliveryapp.domain.interactors.UpdateAddressPatch;

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

    @Provides
    @NonNull
    public AddItemToBasketOrNull provideAddItemToBasketOrNull() {
        return new AddItemToBasketOrNull();
    }

    @Provides
    @NonNull
    public SendOrderToServer provideSendOrderToServer() {
        return new SendOrderToServer();
    }

    @Provides
    @NonNull
    public ClearItemsFromBasket provideClearItemsFromBasket() {
        return new ClearItemsFromBasket();
    }

    @Provides
    @NonNull
    public CreateAccount createAccount() {
        return new CreateAccount();
    }

    @Provides
    @NonNull
    public UpdateAccount updateAccount() {
        return new UpdateAccount();
    }

    @Provides
    @NonNull
    public AddAddress addAddress() {
        return new AddAddress();
    }

    @Provides
    @NonNull
    public UpdateAddress updateAddress() {
        return new UpdateAddress();
    }

    @Provides
    @NonNull
    public UpdateAddressPatch updateAddressPatch() {
        return new UpdateAddressPatch();
    }

    @Provides
    @NonNull
    public DeleteAddress deleteAddress() {
        return new DeleteAddress();
    }

    @Provides
    @NonNull
    public GetAccount getAccount() {
        return new GetAccount();
    }

    @Provides
    @NonNull
    public GetOrders getOrders() {
        return new GetOrders();
    }

    @Provides
    @NonNull
    public CancelOrder cancelOrder() {
        return new CancelOrder();
    }
}
