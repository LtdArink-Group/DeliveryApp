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
    private boolean selected;

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean haveRealSelectedPortion() {
        for(Portion portion : this.getPortions()) {
            if (portion.getCount() > 0) {
                return true;
            }
        }

        return false;
    }

    public Portion getSelectedPortion() {
        for(Portion portion : this.getPortions()) {
            if (portion.getCount() > 0) {
                return portion;
            }
        }

        return this.getPortions()[0];
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
