package ru.arink_group.deliveryapp.presentation.view;

import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;

/**
 * Created by kirillvs on 26.10.17.
 */

public interface FabView {
    void showOrderFab();
    void hideOrderFab();
    FloatingActionButton getFab();
    void changeFragment(Fragment fragment);
}
