package ru.arink_group.deliveryapp.domain;

import java.util.List;

/**
 * Created by kirillvs on 02.10.17.
 */

public class Product {

    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private Portion[] portions;
    private Ingredient[] ingredients;
    private int count;

    public Ingredient[] getSelectedIngredients() {
        // TODO add implementation
        return null;
    }

    public Portion getSelectedPortion() {
        // TODO add implementation
        return null;
    }

    public void setSelectedIngredients(Ingredient[] selectedIngredients) {
        // TODO add implemantation
    }

    public void setSelectedPortion(Portion selectedPortion) {
        // TODO add implementation
    }

    // Getters and Setters

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

    public Portion[] getPortions() {
        return portions;
    }

    public void setPortions(Portion[] portions) {
        this.portions = portions;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
