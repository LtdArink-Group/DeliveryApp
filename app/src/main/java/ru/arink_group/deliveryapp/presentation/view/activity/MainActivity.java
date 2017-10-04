package ru.arink_group.deliveryapp.presentation.view.activity;

import android.support.v7.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.presentation.view.fragment.LoadFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideBar();

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

    private void hideBar() {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
}
