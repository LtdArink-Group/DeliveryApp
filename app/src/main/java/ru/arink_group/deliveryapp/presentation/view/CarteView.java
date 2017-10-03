package ru.arink_group.deliveryapp.presentation.view;

import java.util.List;

import ru.arink_group.deliveryapp.domain.Category;

/**
 * Created by kirillvs on 02.10.17.
 */

public interface CarteView {
    void startCategory(int sectionId);
    void setCategoriesList(List<Category> categories);
    void showErrorMessage(String message);
}
