package ru.arink_group.deliveryapp.presentation.adapters.interfaces;

import android.widget.TextView;

/**
 * Created by kirillvs on 03.10.17.
 */

public interface OnCategoryClickListener<T> {
    void onItemClicked(T model);
}
