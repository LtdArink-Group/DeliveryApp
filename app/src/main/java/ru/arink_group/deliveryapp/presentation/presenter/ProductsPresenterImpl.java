package ru.arink_group.deliveryapp.presentation.presenter;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import ru.arink_group.deliveryapp.domain.Product;
import ru.arink_group.deliveryapp.domain.interactors.GetProductsList;
import ru.arink_group.deliveryapp.presentation.view.ProductsView;

/**
 * Created by kirillvs on 03.10.17.
 */

public class ProductsPresenterImpl implements ProductsPresenter {

    ProductsView productsView;
    private GetProductsList getProductsListUseCase;

    public ProductsPresenterImpl(ProductsView productsView) {
        this.productsView = productsView;
        getProductsListUseCase = new GetProductsList();
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
    }

    @Override
    public void getProducts(int categoryId) {
        getProductsListUseCase.execute(new ProdListObserver(), GetProductsList.Params.forProductsList(categoryId));
    }

    @Override
    public void onProductSelect(int productId) {
        productsView.startProduct(productId);
    }

    private final class ProdListObserver extends DisposableObserver<List<Product>> {

        @Override
        public void onNext(@NonNull List<Product> products) {
            productsView.setProductsList(products);
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
