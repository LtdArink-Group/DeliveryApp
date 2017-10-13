package ru.arink_group.deliveryapp.presentation.adapters;

import android.view.View;
import android.widget.TextView;

/**
 * Created by kirillvs on 03.10.17.
 */

public interface OnItemClickListener <T> {
    void onItemClicked(boolean isAdd, T model , View productView);
}
