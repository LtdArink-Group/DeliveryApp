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
import ru.arink_group.deliveryapp.presentation.view.fragment.CarteFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.ProductsFragment;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        ProductsFragment pf = new ProductsFragment();

        Intent mIntent = getIntent();

        Bundle bundle = new Bundle();
        bundle.putInt(CarteFragment.CATEGORY, mIntent.getIntExtra(CarteFragment.CATEGORY, 0));
        pf.setArguments(bundle);
        fragmentTransaction.add(R.id.fragment_container, pf);
        fragmentTransaction.commit();

    }

}
