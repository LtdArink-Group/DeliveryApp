package ru.arink_group.deliveryapp.presentation.presenter;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import ru.arink_group.deliveryapp.domain.Category;
import ru.arink_group.deliveryapp.domain.interactors.GetCategoriesList;
import ru.arink_group.deliveryapp.presentation.view.CarteView;

/**
 * Created by kirillvs on 02.10.17.
 */

public class CartePresenterImpl implements CartePresenter {

    private CarteView carteView;
    private GetCategoriesList getCategoriesListUseCase;

    public CartePresenterImpl(CarteView carteView) {
        this.carteView = carteView;
        getCategoriesListUseCase = new GetCategoriesList();
    }

    @Override
    public void onItemSelected(int sectionId, String name) {
        carteView.startCategory(sectionId, name);
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
            carteView.setCategoriesList(categories);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            carteView.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
            carteView.loadCompleted();
        }
    }


}
