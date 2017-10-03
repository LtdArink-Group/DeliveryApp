package ru.arink_group.deliveryapp.presentation.presenter;

import android.app.Fragment;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.presentation.view.MenuView;
import ru.arink_group.deliveryapp.presentation.view.fragment.AccountFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.BasketFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.CarteFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.OrdersFragment;

/**
 * Created by kirillvs on 02.10.17.
 */

public class MenuPresenterImpl implements MenuPresenter {

    private MenuView menuView;

    public MenuPresenterImpl(MenuView menuView) {
        this.menuView = menuView;
    }

    @Override
    public void onItemMenuSelect(int itemId) {

        Fragment fragment;

        if (itemId == R.id.carte) {
            fragment = new CarteFragment();
        } else if (itemId == R.id.orders) {
            fragment = new OrdersFragment();
        } else if (itemId == R.id.basket) {
            fragment = new BasketFragment();
        } else if (itemId == R.id.account) {
            fragment = new AccountFragment();
        } else if (itemId == R.id.nav_share) {
            fragment = new CarteFragment(); // TODO change fragment
        } else if (itemId == R.id.nav_send) {
            fragment = new CarteFragment(); // TODO change fragment
        } else {
            fragment = new CarteFragment();
        }

        menuView.changeFragment(fragment);

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
