package ru.arink_group.deliveryapp;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.UUID;

import ru.arink_group.deliveryapp.presentation.di.component.AppComponent;
import ru.arink_group.deliveryapp.presentation.di.component.DaggerAppComponent;
import ru.arink_group.deliveryapp.presentation.di.module.AppModule;
import ru.arink_group.deliveryapp.presentation.di.module.InteractorsModule;

/**
 * Created by kirillvs on 06.10.17.
 */

public class App extends Application {

    private static String unigueID = null;
    private static final String PREF_UNIQ_ID = "PREF_UNIQ_ID";

    public static final String DEVICE_NAME = "android";

    public static final String APP_SHARED_PREF = "BOOKING FOOD SHARED PREF";
    public static final String COMPANY_INFO = "COMPANY INFO";

    private static AppComponent component;
    public static AppComponent getComponent() {
        return component;
    }

    public static String getCompanyId() {
        return "1";
    }

    public static String getUUID() {
        return unigueID;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();

        SharedPreferences sharedPreferences = this.getSharedPreferences(PREF_UNIQ_ID, App.MODE_PRIVATE);
        unigueID = sharedPreferences.getString(PREF_UNIQ_ID, null);

        if(unigueID == null) {
            unigueID = UUID.randomUUID().toString();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(PREF_UNIQ_ID, unigueID);
            editor.apply();
        }

    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .interactorsModule(new InteractorsModule())
                .build();
    }
}
