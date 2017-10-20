package ru.arink_group.deliveryapp.domain.dto;

import java.util.ArrayList;
import java.util.List;

import ru.arink_group.deliveryapp.domain.Category;
import ru.arink_group.deliveryapp.domain.Ingredient;
import ru.arink_group.deliveryapp.domain.Portion;
import ru.arink_group.deliveryapp.domain.Product;

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

    public static Product transformProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setCount(0);
        product.setImageUrl(productDTO.getPhoto());
        product.setPortions(transformPortions(productDTO.getMainOptions()));
        product.setIngredients(transformIngredients(productDTO.getAdditionalInfo()));
        return product;
    }

    public static List<Product> transformProducts(List<ProductDTO> productDTOs) {
        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO : productDTOs) {
            products.add(transformProduct(productDTO));
        }
        return products;
    }

    public static Portion transformPortion(MainOptionDTO mainOption) {
        Portion portion = new Portion();
        portion.setName(mainOption.getName());
        portion.setPrice(mainOption.getCost());
        return portion;
    }

    public static Portion[] transformPortions(List<MainOptionDTO> mainOptions) {
        Portion[] portions = new Portion[mainOptions.size()];
        for (int i = 0; i < portions.length; i++) {
            portions[i] = transformPortion(mainOptions.get(i));
        }
        return portions;
    }

    public static Ingredient transformIngredient(AdditionalInfoDTO additionalInfo) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(additionalInfo.getName());
        ingredient.setCount(0);
        ingredient.setPrice(additionalInfo.getCost());
        return ingredient;
    }

    public static Ingredient[] transformIngredients(List<AdditionalInfoDTO> additionalInfoDTOs) {
        Ingredient[] ingredients = new Ingredient[additionalInfoDTOs.size()];
        for (int i = 0; i < ingredients.length; i++) {
            ingredients[i] = transformIngredient(additionalInfoDTOs.get(i));
        }
        return ingredients;
    }


}
