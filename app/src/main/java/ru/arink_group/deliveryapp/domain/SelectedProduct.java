package ru.arink_group.deliveryapp.domain;

/**
 * Created by kirillvs on 09.10.17.
 */

public class SelectedProduct {

    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private Portion selectedPportion;
    private SelectedIngredient[] selectedIngredients;

    public SelectedProduct() {

    }

    public SelectedProduct(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.imageUrl = product.getImageUrl();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Portion getSelectedPportion() {
        return selectedPportion;
    }

    public void setSelectedPportion(Portion selectedPportion) {
        this.selectedPportion = selectedPportion;
    }

    public SelectedIngredient[] getSelectedIngredients() {
        return selectedIngredients;
    }

    public void setSelectedIngredients(SelectedIngredient[] selectedIngredients) {
        this.selectedIngredients = selectedIngredients;
    }

    public void setSelectedIngredientsFromIngredients(Ingredient[] ingredients) {
        this.selectedIngredients = SelectedIngredient.fromIngredients(ingredients);
    }
}
