package ru.arink_group.deliveryapp.presentation.view.activity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.MenuPresenter;
import ru.arink_group.deliveryapp.presentation.presenter.MenuPresenterImpl;
import ru.arink_group.deliveryapp.presentation.view.FabView;
import ru.arink_group.deliveryapp.presentation.view.MenuView;
import ru.arink_group.deliveryapp.presentation.view.fragment.AccountFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.CategoriesFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.NewsFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.OrderFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.OrdersHistoryFragment;

public class MenuActivity extends AppCompatActivity
        implements MenuView, NavigationView.OnNavigationItemSelectedListener, FabView {

    public static final String IS_ORDER_START = "is order start";
    public static final String IS_ACCOUNT_START = "is account start";
    public static final String IS_HISTORY_START = "is history start";
    public static final String IS_NEWS_START = "is news start";

    private MenuPresenter menuPresenter;
    private FloatingActionButton fab;
    private ProgressBar progressBar;
    private FrameLayout content;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                if (inputManager != null) {
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        menuPresenter = new MenuPresenterImpl(this);

        progressBar = findViewById(R.id.progress);
        content = findViewById(R.id.menu_fragment);


        starterFragment();

    }

    private void starterFragment() {
        //TODO that is the bug!!!!!!!!!!!!!!!!!!!!!! i use add instead of replace
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if(getIntent().getBooleanExtra(IS_ORDER_START, false)) {
            fragmentTransaction.add(R.id.menu_fragment, new OrderFragment());
            navigationView.getMenu().getItem(2).setChecked(true);
        } else if(getIntent().getBooleanExtra(IS_ACCOUNT_START, false)){
            AccountFragment accountFragment = new AccountFragment();
            fragmentTransaction.add(R.id.menu_fragment, accountFragment);
            navigationView.getMenu().getItem(3).setChecked(true);
        } else if(getIntent().getBooleanExtra(IS_HISTORY_START, false)){
            OrdersHistoryFragment orderHistoryFragment = new OrdersHistoryFragment();
            fragmentTransaction.add(R.id.menu_fragment, orderHistoryFragment);
            navigationView.getMenu().getItem(1).setChecked(true);
        } else if (getIntent().getBooleanExtra(IS_NEWS_START,false)){
            NewsFragment newsFragment=new NewsFragment();
            fragmentTransaction.add(R.id.menu_fragment,newsFragment);
            navigationView.getMenu().getItem(4).setChecked(true);
        }
        else {
            fragmentTransaction.add(R.id.menu_fragment, new CategoriesFragment());
            navigationView.getMenu().getItem(0).setChecked(true);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (isTaskRoot())
                new AlertDialog.Builder(this)
                        .setMessage(R.string.whant_to_quit)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MenuActivity.super.onBackPressed();
                            }
                        })
                        .setNegativeButton(R.string.no, null).show();
            else super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu, menu); // TODO remove until it's functionality will be needed
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        menuPresenter.onItemMenuSelect(id);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        menuPresenter.onItemMenuSelect(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void changeFragment(Fragment fragment) {
        if (fragment instanceof OrderFragment) {
            navigationView.getMenu().getItem(2).setChecked(true);
        } else if (fragment instanceof OrdersHistoryFragment) {
            navigationView.getMenu().getItem(1).setChecked(true);
        } else if (fragment instanceof AccountFragment) {
            navigationView.getMenu().getItem(4).setChecked(true);
        } else if (fragment instanceof NewsFragment) {
            navigationView.getMenu().getItem(3).setChecked(true);
        } else {
            navigationView.getMenu().getItem(0).setChecked(true);
        }
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.replace(R.id.menu_fragment, fragment);
//        fragmentTransaction.addToBackStack(null); //TODO Разобраться в баге с переходом,старый фрагмент остается на экране позади
        fragmentTransaction.commit();
    }

    @Override
    public void changeFragmentToPlaceHolder(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.replace(R.id.menu_fragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void goToAboutCompany() {
        Intent intent = new Intent(this, AboutCompanyActivity.class);
        startActivity(intent);
    }

    @Override
    public void loadingStart() {
        content.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadingFinish() {
        progressBar.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
    }

//    @Override
//    public void contentLoaded() {
////        menuPresenter.onItemMenuSelect(R.id.carte);
//    }

    @Override
    public void showOrderFab() {
        fab.show();
    }

    @Override
    public void hideOrderFab(){
        fab.hide();
    }

    @Override
    public FloatingActionButton getFab() {
        return fab;
    }
}
