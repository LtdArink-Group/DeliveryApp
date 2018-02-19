package ru.arink_group.deliveryapp.presentation.presenter.interfaces;

/**
 * Created by kirillvs on 02.10.17.
 */

public interface CategoriesPresenter extends Presenter {

    void onItemSelected(int sectionId, String name);
    void onCartPressed(); //no need now
    void getCategoriesList();
    void updateTotals();
}
