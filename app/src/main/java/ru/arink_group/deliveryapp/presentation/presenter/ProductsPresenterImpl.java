package ru.arink_group.deliveryapp.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import ru.arink_group.deliveryapp.domain.dao.Product;
import ru.arink_group.deliveryapp.domain.interactors.AddItemToBasket;
import ru.arink_group.deliveryapp.domain.interactors.AddListItemsToBasket;
import ru.arink_group.deliveryapp.domain.interactors.GetListItemsFromBasket;
import ru.arink_group.deliveryapp.domain.interactors.GetProductsList;
import ru.arink_group.deliveryapp.App;
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.ProductsPresenter;
import ru.arink_group.deliveryapp.presentation.view.ProductsView;

/**
 * Created by kirillvs on 03.10.17.
 */

public class ProductsPresenterImpl extends BasePresenter implements ProductsPresenter {

    ProductsView productsView;

    List<Product> productsForBasket;

    @Inject GetProductsList getProductsListUseCase;
    @Inject GetListItemsFromBasket getListItemsFromBasket;
    @Inject AddListItemsToBasket addListItemsToBasket;
    @Inject AddItemToBasket addItemToBasket;

    public ProductsPresenterImpl(ProductsView productsView) {
        this.productsView = productsView;
        App.getComponent().inject(this);
        productsForBasket = new ArrayList<>();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getProductsListUseCase.dispose();
        getListItemsFromBasket.dispose();
        addListItemsToBasket.dispose();
        addItemToBasket.dispose();
    }

    @Override
    public void getProducts(int categoryId) {
        getProductsListUseCase.execute(new ProdListObserver(), GetProductsList.Params.forProductsList(categoryId));
    }

    @Override
    public void updateProductsFromBasket() {
        getListItemsFromBasket.execute(new SelectedListObserver(), null);
    }

    @Override
    public void addItemsListToBasket() {
        // TODO REWORK or NO NEED
    }

    @Override
    public void addItemToBasket(Product product) {
        addItemToBasket.execute(new AddProductToBasketObserver(), AddItemToBasket.Params.forBasketAddItem(product));
    }

    private final class ProdListObserver extends DisposableObserver<List<Product>> {

        @Override
        public void onNext(@NonNull List<Product> products) {
            if (products.size() > 0) {
                productsView.setProductsList(products);
                ProductsPresenterImpl.this.updateProductsFromBasket();
            } else {
                productsView.showPlaceholder();
            }
        }

        @Override
        public void onError(@NonNull Throwable e) {
            productsView.showErrorMessage(handleGetNetError(e));
        }

        @Override
        public void onComplete() {
            productsView.loadingFinish();
        }
    }

    private final class SelectedListObserver extends DisposableObserver<List<Product>> {

        @Override
        public void onNext(@NonNull List<Product> selectedProducts) {
            productsView.updateProductList(selectedProducts);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            productsView.showErrorMessage(handleInternalError(e));
        }

        @Override
        public void onComplete() {

        }
    }

    private final class AddProductToBasketObserver extends DisposableObserver<Boolean> {

        @Override
        public void onNext(@NonNull Boolean aBoolean) {
            // TODO Do Something, maybe update state
        }

        @Override
        public void onError(@NonNull Throwable e) {
            productsView.showErrorMessage(handleInternalError(e));
        }

        @Override
        public void onComplete() {

        }
    }

    private final class AddListItemsObserver extends DisposableObserver<Integer> {

        @Override
        public void onNext(@NonNull Integer addedRows) {
            productsView.showErrorMessage(String.valueOf(addedRows));
        }

        @Override
        public void onError(@NonNull Throwable e) {
            productsView.showErrorMessage(handleInternalError(e));
        }

        @Override
        public void onComplete() {

        }
    }


}
