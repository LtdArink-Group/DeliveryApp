package ru.arink_group.deliveryapp.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.presentation.Menu.MenuActivity;
import ru.arink_group.deliveryapp.presentation.Menu.MenuActivity2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
