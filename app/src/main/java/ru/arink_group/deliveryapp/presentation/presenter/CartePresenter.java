package ru.arink_group.deliveryapp.presentation.presenter;

/**
 * Created by kirillvs on 02.10.17.
 */

public interface CartePresenter extends Presenter{

    void onItemSelected(int sectionId);
    void onCartPressed(); //no need now
    void getCategoriesList();
}
