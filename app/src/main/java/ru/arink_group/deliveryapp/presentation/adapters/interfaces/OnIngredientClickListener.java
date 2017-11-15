package ru.arink_group.deliveryapp.presentation.adapters.interfaces;

import ru.arink_group.deliveryapp.domain.dao.Product;

/**
 * Created by kirillvs on 16.10.17.
 */

public interface OnIngredientClickListener {
    void onIngredientClicked(Product product);
}
