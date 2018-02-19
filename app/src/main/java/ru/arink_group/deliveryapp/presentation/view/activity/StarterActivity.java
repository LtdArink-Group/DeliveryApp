package ru.arink_group.deliveryapp.presentation.view.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.net.ConnectException;

import io.reactivex.observers.DisposableObserver;
import ru.arink_group.deliveryapp.App;
import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.dao.Company;
import ru.arink_group.deliveryapp.domain.interactors.GetCompany;
import ru.arink_group.deliveryapp.domain.interactors.GetCompanyFromShared;
import ru.arink_group.deliveryapp.presentation.shared.ErrorsTranslator;
import ru.arink_group.deliveryapp.presentation.view.fragment.LoadFragment;

public class StarterActivity extends AppCompatActivity {

    GetCompany getCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideBar();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.load_fragment, new LoadFragment());
        fragmentTransaction.commit();

        getCompany = new GetCompany();
        getCompany.execute(new CompanyDisposableObserver(), null);
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
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    public class CompanyDisposableObserver extends DisposableObserver<Company> {

        @Override
        public void onNext(Company company) {
            SharedPreferences sp = getSharedPreferences(App.APP_SHARED_PREF, Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            Gson gson = new Gson();
            String companyJson = gson.toJson(company);
            editor.putString(App.COMPANY_INFO, companyJson);
            editor.apply();
            GetCompanyFromShared.INSTANCE.setCompany(company);
        }

        @Override
        public void onError(Throwable e) {
            if (e instanceof ConnectException)
                Toast.makeText(StarterActivity.this, ErrorsTranslator.translate("connection"), Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(StarterActivity.this, ErrorsTranslator.translate("internal"), Toast.LENGTH_SHORT).show();

            e.printStackTrace();
            GetCompanyFromShared.INSTANCE.loadCompany(StarterActivity.this);
            startApp();
        }

        @Override
        public void onComplete() {
            startApp();
        }
    }


}
