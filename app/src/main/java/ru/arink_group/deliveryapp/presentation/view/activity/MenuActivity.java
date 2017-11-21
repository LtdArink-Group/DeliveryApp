package ru.arink_group.deliveryapp.presentation.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
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
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.MenuPresenter;
import ru.arink_group.deliveryapp.presentation.presenter.MenuPresenterImpl;
import ru.arink_group.deliveryapp.presentation.view.FabView;
import ru.arink_group.deliveryapp.presentation.view.MenuView;
import ru.arink_group.deliveryapp.presentation.view.fragment.AccountFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.CategoriesFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.OrderFragment;

public class MenuActivity extends AppCompatActivity
        implements MenuView, NavigationView.OnNavigationItemSelectedListener, FabView {

    public static final String IS_ORDER_START = "is order start";
    public static final String IS_ACCOUNT_START = "is account start";

    private MenuPresenter menuPresenter;
    private FloatingActionButton fab;
    private ProgressBar progressBar;
    private FrameLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        } else if(getIntent().getBooleanExtra(IS_ACCOUNT_START, false)){
            AccountFragment accountFragment = new AccountFragment();
            fragmentTransaction.add(R.id.menu_fragment, accountFragment);
        } else {
            fragmentTransaction.add(R.id.menu_fragment, new CategoriesFragment());
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
