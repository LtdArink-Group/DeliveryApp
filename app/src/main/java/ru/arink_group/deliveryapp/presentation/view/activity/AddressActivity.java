package ru.arink_group.deliveryapp.presentation.view.activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.dao.Address;
import ru.arink_group.deliveryapp.presentation.view.ProgressView;
import ru.arink_group.deliveryapp.presentation.view.fragment.AddressFragment;
import ru.arink_group.deliveryapp.presentation.view.fragment.OrderFragment;

public class AddressActivity extends AppCompatActivity implements ProgressView{

    public static final String ADDRESS_SER = "addres_ser";

    AddressFragment addressFragment;

    private ProgressBar progressBar;
    private FrameLayout content;
    private boolean startOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_address);

        addressFragment = new AddressFragment();
        Intent params = getIntent();
        startOrder = params.getBooleanExtra(OrderFragment.REDIRECT_TO_ORDER, false);
        Address address = (Address) params.getSerializableExtra(ADDRESS_SER);
        FragmentTransaction fm = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ADDRESS_SER, address);
        bundle.putBoolean(OrderFragment.REDIRECT_TO_ORDER, startOrder);
        addressFragment.setArguments(bundle);
        fm.add(R.id.frame_address, addressFragment);
        fm.commit();

        progressBar = findViewById(R.id.address_progress);
        content = findViewById(R.id.frame_address);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        if(item.getItemId() == R.id.ok_button) {
            addressFragment.onClick(null);
        } else {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ok_menu, menu);
        return super.onCreateOptionsMenu(menu);
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
}
