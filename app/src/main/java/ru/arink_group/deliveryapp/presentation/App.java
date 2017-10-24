package ru.arink_group.deliveryapp.presentation;

import android.app.Application;

import ru.arink_group.deliveryapp.presentation.di.component.AppComponent;
import ru.arink_group.deliveryapp.presentation.di.component.DaggerAppComponent;
import ru.arink_group.deliveryapp.presentation.di.module.AppModule;
import ru.arink_group.deliveryapp.presentation.di.module.InteractorsModule;

/**
 * Created by kirillvs on 06.10.17.
 */

public class App extends Application {

    private static AppComponent component;
    public static AppComponent getComponent() {
        return component;
    }

    public static String getCompanyId() {
        return "6";
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .interactorsModule(new InteractorsModule())
                .build();
    }
}
