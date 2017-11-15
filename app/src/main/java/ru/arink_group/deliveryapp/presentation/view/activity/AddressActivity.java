package ru.arink_group.deliveryapp.presentation.view.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.dao.Address;
import ru.arink_group.deliveryapp.presentation.view.fragment.AddressFragment;

public class AddressActivity extends AppCompatActivity {

    public static final String ADDRESS_SER = "addres_ser";

    AddressFragment addressFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_address);

        addressFragment = new AddressFragment();
        Intent params = getIntent();
        Address address = (Address) params.getSerializableExtra(ADDRESS_SER);
        FragmentTransaction fm = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ADDRESS_SER, address);
        addressFragment.setArguments(bundle);
        fm.add(R.id.frame_address, addressFragment);
        fm.commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
}
