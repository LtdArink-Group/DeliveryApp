package ru.arink_group.deliveryapp.presentation.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.arink_group.deliveryapp.presentation.di.module.AppModule;
import ru.arink_group.deliveryapp.presentation.di.module.InteractorsModule;
import ru.arink_group.deliveryapp.presentation.presenter.CartePresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.ProductsPresenterImpl;

/**
 * Created by kirillvs on 06.10.17.
 */


@Component(modules = {AppModule.class, InteractorsModule.class})
@Singleton
public interface AppComponent {
    void inject(CartePresenterImpl carte);
    void inject(ProductsPresenterImpl products);
}
