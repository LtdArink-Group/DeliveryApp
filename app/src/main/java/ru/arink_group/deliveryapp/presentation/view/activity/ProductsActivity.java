package ru.arink_group.deliveryapp.presentation.view.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.presentation.view.FabView;
import ru.arink_group.deliveryapp.presentation.view.fragment.CategoriesFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.ProductsFragment;

public class ProductsActivity extends AppCompatActivity implements FabView {

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductsActivity.this, MenuActivity.class);
                intent.putExtra(MenuActivity.IS_ORDER_START, true);
                ProductsActivity.this.startActivity(intent);
            }
        });

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        ProductsFragment pf = new ProductsFragment();

        Intent mIntent = getIntent();

        setTitle(mIntent.getStringExtra(CategoriesFragment.CATEGORY_NAME));

        Bundle bundle = new Bundle();
        bundle.putInt(CategoriesFragment.CATEGORY, mIntent.getIntExtra(CategoriesFragment.CATEGORY, 0));
        bundle.putString(CategoriesFragment.CATEGORY_NAME, mIntent.getStringExtra(CategoriesFragment.CATEGORY_NAME));
        pf.setArguments(bundle);
        fragmentTransaction.add(R.id.fragment_container, pf);
        fragmentTransaction.commit();

    }

    @Override
    public void showOrderFab() {
        fab.show();
    }

    @Override
    public void hideOrderFab() {
        fab.hide();
    }
}
