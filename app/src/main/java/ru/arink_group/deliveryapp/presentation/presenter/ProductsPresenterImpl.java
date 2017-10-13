package ru.arink_group.deliveryapp.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import ru.arink_group.deliveryapp.domain.Product;
import ru.arink_group.deliveryapp.domain.interactors.AddListItemsToBasket;
import ru.arink_group.deliveryapp.domain.interactors.GetListItemsFromBasket;
import ru.arink_group.deliveryapp.domain.interactors.GetProductsList;
import ru.arink_group.deliveryapp.presentation.App;
import ru.arink_group.deliveryapp.presentation.view.ProductsView;

/**
 * Created by kirillvs on 03.10.17.
 */

public class ProductsPresenterImpl implements ProductsPresenter {

    ProductsView productsView;

    List<Product> productsForBasket;

    @Inject GetProductsList getProductsListUseCase;
    @Inject GetListItemsFromBasket getListItemsFromBasket;
    @Inject AddListItemsToBasket addListItemsToBasket;

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
    }

    @Override
    public void getProducts(int categoryId) {
        getProductsListUseCase.execute(new ProdListObserver(), GetProductsList.Params.forProductsList(categoryId));
    }

    private void updateProductsFromBasket() {
        getListItemsFromBasket.execute(new SelectedListObserver(), null);
    }

    @Override
    public int onProductSelect(boolean isAdd, Product product) {
        if (isAdd){
            return 100;
        }
        return 5;
    }

    private int addProductToBasketList(Product product) {
        return 0;
    }

    private int removeProductFromBasketList(Product product) {
        return 0;
    }

    private List<Product> getFinalProductsList() {
        return null;
    }

    @Override
    public void addItemsToCart() {
        // TODO REWORK
        List<Product> sps = getFinalProductsList();
        addListItemsToBasket.execute(new AddListItemsObserver(), AddListItemsToBasket.Params.forBasketAddItemsList(sps));
    }

    private final class ProdListObserver extends DisposableObserver<List<Product>> {

        @Override
        public void onNext(@NonNull List<Product> products) {
            productsView.setProductsList(products);
            ProductsPresenterImpl.this.updateProductsFromBasket();
        }

        @Override
        public void onError(@NonNull Throwable e) {
            productsView.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
        }
    }

    private final class SelectedListObserver extends DisposableObserver<List<Product>> {

        @Override
        public void onNext(@NonNull List<Product> selectedProducts) {
            productsView.updateProductList(selectedProducts);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            productsView.showErrorMessage(e.getMessage());
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
            productsView.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }


}
