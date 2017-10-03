package ru.arink_group.deliveryapp.domain.interactors;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.data.repository.CategoriesDataRepository;
import ru.arink_group.deliveryapp.domain.Category;
import ru.arink_group.deliveryapp.domain.repository.CategoriesRepository;

/**
 * Created by kirillvs on 03.10.17.
 */

public class GetCategoriesList extends UseCase <List<Category>, Void> {

    CategoriesRepository categoriesRepository;

    public GetCategoriesList() {
        super();
        this.categoriesRepository = CategoriesDataRepository.singletonCategoriesRepository();
    }

    @Override
    Observable<List<Category>> buildUseCaseObservable(Void unused) {
        return this.categoriesRepository.categoriesList();
    }
}
