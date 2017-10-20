package ru.arink_group.deliveryapp.data.net.response;

import java.util.List;

import ru.arink_group.deliveryapp.domain.dto.CategoryDTO;

/**
 * Created by kirillvs on 20.10.17.
 */

public class CategoriesObject {

    private List<CategoryDTO> categories;

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }
}
