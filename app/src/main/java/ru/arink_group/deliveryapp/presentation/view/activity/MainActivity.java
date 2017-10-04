package ru.arink_group.deliveryapp.presentation.view.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.presentation.view.fragment.LoadFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.load_fragment, new LoadFragment());
        fragmentTransaction.commit();

        final Handler handler = new Handler();
        final Runnable r = new Runnable()
        {
            public void run()
            {
                startApp();
            }
        };

        handler.postDelayed(r, 1000);


    }

    private void startApp() {

        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);

    }
}
