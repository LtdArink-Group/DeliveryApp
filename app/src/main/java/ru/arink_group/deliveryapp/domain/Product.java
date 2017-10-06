package ru.arink_group.deliveryapp.domain;

import java.util.List;

/**
 * Created by kirillvs on 02.10.17.
 */

public class Product {

    private int id;
    private String name;
    private String description;
    private String price;
    private String[] size;
    private String imageUrl;
    private String unit;
    private Portion[] portions;
    private Ingredient[] ingredients;

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public Portion[] getPortions() {
        return portions;
    }

    public void setPortions(Portion[] portions) {
        this.portions = portions;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String[] getSize() {
        return size;
    }

    public void setSize(String[] size) {
        this.size = size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
