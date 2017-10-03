package ru.arink_group.deliveryapp.presentation.presenter;

import android.app.Fragment;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.presentation.view.MenuView;
import ru.arink_group.deliveryapp.presentation.view.fragment.CarteFragment;

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

        fragment = new CarteFragment(); // TODO move to if clause

        if (itemId == R.id.carte) {

        } else if (itemId == R.id.orders) {

        } else if (itemId == R.id.basket) {

        } else if (itemId == R.id.account) {

        } else if (itemId == R.id.nav_share) {

        } else if (itemId == R.id.nav_send) {

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
