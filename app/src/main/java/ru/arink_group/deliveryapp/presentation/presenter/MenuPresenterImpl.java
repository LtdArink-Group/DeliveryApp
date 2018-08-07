package ru.arink_group.deliveryapp.presentation.presenter;

import android.app.Fragment;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.MenuPresenter;
import ru.arink_group.deliveryapp.presentation.view.MenuView;
import ru.arink_group.deliveryapp.presentation.view.fragment.AccountFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.NewsFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.OrderFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.CategoriesFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.OrdersHistoryFragment;

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
            fragment = new CategoriesFragment();
        } else if (itemId == R.id.orders) {
            fragment = new OrdersHistoryFragment();
        } else if (itemId == R.id.news) {
            fragment = new NewsFragment();
        } else if (itemId == R.id.basket) {
            fragment = new OrderFragment();
        } else if (itemId == R.id.account) {
            fragment = new AccountFragment();
        } else if (itemId == R.id.about_company) {
            menuView.goToAboutCompany();
            return;
        } else if (itemId == R.id.rewievers) {
            fragment = new CategoriesFragment(); // TODO change fragment
        } else {
            fragment = new CategoriesFragment();
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
