package ru.arink_group.deliveryapp.presentation.view;

import android.app.Fragment;

/**
 * Created by kirillvs on 02.10.17.
 */

public interface MenuView extends ProgressView{

    void changeFragment(Fragment frag);
    void changeFragmentToPlaceHolder(Fragment frag);
    void goToAboutCompany();
}
