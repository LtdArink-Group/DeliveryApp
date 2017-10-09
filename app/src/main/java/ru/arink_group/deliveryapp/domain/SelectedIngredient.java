package ru.arink_group.deliveryapp.domain;

/**
 * Created by kirillvs on 09.10.17.
 */

public class SelectedIngredient {

    private int id;
    private String name;
    private float price;
    private String description;
    private String size;
    private String imageLink;

    private int count;

    public SelectedIngredient() {

    }

    public SelectedIngredient(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
        this.price = Float.parseFloat(ingredient.getPrice());
        this.description = ingredient.getDescription();
        this.size = ingredient.getSize();
        this.imageLink = ingredient.getImageLink();
    }

    public static SelectedIngredient[] fromIngredients(Ingredient[] ingredients) {
        SelectedIngredient[] selectedIngredients = new SelectedIngredient[ingredients.length];
        for(int i = 0; i < selectedIngredients.length; i++) {
            selectedIngredients[i] = new SelectedIngredient(ingredients[i]);
        }
        return selectedIngredients;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
