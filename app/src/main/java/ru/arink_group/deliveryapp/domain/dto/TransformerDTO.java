package ru.arink_group.deliveryapp.domain.dto;

import java.util.ArrayList;
import java.util.List;

import ru.arink_group.deliveryapp.domain.Category;

/**
 * Created by kirillvs on 20.10.17.
 */

public class TransformerDTO {
    public static Category transformCategory(CategoryDTO categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setIcon(categoryDto.getIconType());
        category.setDescription(categoryDto.getDescription());

        return category;
    }

    public static List<Category> transformListCategories(List<CategoryDTO> categoriesDTO) {
        List<Category> categories = new ArrayList<>();

        for(CategoryDTO categoryDTO : categoriesDTO) {
            categories.add(transformCategory(categoryDTO));
        }

        return categories;
    }
}
