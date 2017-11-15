package ru.arink_group.deliveryapp.domain.interactors;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import ru.arink_group.deliveryapp.data.repository.CategoriesDataRepository;
import ru.arink_group.deliveryapp.domain.dao.Category;
import ru.arink_group.deliveryapp.domain.dto.CategoryDTO;
import ru.arink_group.deliveryapp.domain.dto.TransformerDTO;
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
        return this.categoriesRepository.categoriesList().map(new Function<List<CategoryDTO>, List<Category>>() {
            @Override
            public List<Category> apply(@NonNull List<CategoryDTO> categories) throws Exception {
                return TransformerDTO.transformListCategories(categories);
            }
        });
    }
}
