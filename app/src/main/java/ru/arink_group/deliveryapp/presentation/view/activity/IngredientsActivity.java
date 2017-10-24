package ru.arink_group.deliveryapp.presentation.view.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.Product;
import ru.arink_group.deliveryapp.presentation.view.fragment.IngredientsFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.ProductsFragment;

import static ru.arink_group.deliveryapp.presentation.view.fragment.CategoriesFragment.CATEGORY;
import static ru.arink_group.deliveryapp.presentation.view.fragment.CategoriesFragment.CATEGORY_NAME;

public class IngredientsActivity extends AppCompatActivity {

    private int categoryId;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ингредиенты");


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

}
