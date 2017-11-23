package ru.arink_group.deliveryapp.presentation.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.dao.Product;
import ru.arink_group.deliveryapp.presentation.view.FabView;
import ru.arink_group.deliveryapp.presentation.view.fragment.IngredientsFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.ProductsFragment;

import static ru.arink_group.deliveryapp.presentation.view.fragment.CategoriesFragment.CATEGORY;
import static ru.arink_group.deliveryapp.presentation.view.fragment.CategoriesFragment.CATEGORY_NAME;

public class IngredientsActivity extends AppCompatActivity implements FabView {

    private int categoryId;
    private String categoryName;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ингредиенты");

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IngredientsActivity.this, MenuActivity.class);
                intent.putExtra(MenuActivity.IS_ORDER_START, true);
                IngredientsActivity.this.startActivity(intent);
            }
        });

        Intent mIntent = getIntent();

        Product product = (Product) mIntent.getSerializableExtra(ProductsFragment.PRODUCT);

        categoryId = mIntent.getIntExtra(CATEGORY, 1);
        categoryName = mIntent.getStringExtra(CATEGORY_NAME);

        Bundle bundle = new Bundle();
        bundle.putSerializable(ProductsFragment.PRODUCT, product);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        IngredientsFragment ingredientsFragment = new IngredientsFragment();

        ingredientsFragment.setArguments(bundle);

        ft.add(R.id.ingredient_frame, ingredientsFragment);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        upIntent.putExtra(CATEGORY, categoryId);
        upIntent.putExtra(CATEGORY_NAME, categoryName);

        NavUtils.navigateUpTo(this, upIntent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            Intent upIntent = NavUtils.getParentActivityIntent(this);
            upIntent.putExtra(CATEGORY, categoryId);
            upIntent.putExtra(CATEGORY_NAME, categoryName);

            NavUtils.navigateUpTo(this, upIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showOrderFab() {
        fab.show();
    }

    @Override
    public void hideOrderFab() {
        fab.hide();
    }

    @Override
    public FloatingActionButton getFab() {
        return fab;
    }

    @Override
    public void changeFragment(Fragment fragment) {
        // no-op
    }
}
