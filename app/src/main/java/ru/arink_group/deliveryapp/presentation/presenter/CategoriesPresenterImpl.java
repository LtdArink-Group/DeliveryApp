package ru.arink_group.deliveryapp.presentation.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import ru.arink_group.deliveryapp.domain.dao.Category;
import ru.arink_group.deliveryapp.domain.interactors.GetCategoriesList;
import ru.arink_group.deliveryapp.App;
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.CategoriesPresenter;
import ru.arink_group.deliveryapp.presentation.view.CategoriesView;

/**
 * Created by kirillvs on 02.10.17.
 */

public class CategoriesPresenterImpl implements CategoriesPresenter {

    private CategoriesView categoriesView;
    @Inject GetCategoriesList getCategoriesListUseCase;

    public CategoriesPresenterImpl(CategoriesView categoriesView) {
        this.categoriesView = categoriesView;
        App.getComponent().inject(this);
    }

    @Override
    public void onItemSelected(int sectionId, String name) {
        categoriesView.startCategory(sectionId, name);
    }

    @Override
    public void onCartPressed() {
        //TODO Need or no?
    }

    @Override
    public void getCategoriesList() {
        getCategoriesListUseCase.execute(new CategoryListObserver(), null);

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getCategoriesListUseCase.dispose();
    }

    private final class CategoryListObserver extends DisposableObserver<List<Category>> {

        @Override
        public void onNext(@NonNull List<Category> categories) {
            categoriesView.setCategoriesList(categories);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            categoriesView.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
            categoriesView.loadCompleted();
        }
    }


}
