package ru.arink_group.deliveryapp.domain.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kirillvs on 02.10.17.
 */

public class Product implements Serializable {

    private int id;
    private String name;
    private String brief;
    private String description;
    private String imageUrl;
    private Portion[] portions;
    private Ingredient[] ingredients;
    private int count;

    private static Product nothing;

    public static Product NOTHING() {
        if(nothing == null) nothing = new Product();
        return nothing;
    }

    public Ingredient[] getSelectedIngredients() {
        List<Ingredient> sis = new ArrayList<>();

        for(Ingredient ingredient : ingredients) {
            if (ingredient.getCount() > 0) sis.add(ingredient);
        }

        Ingredient[] selectedIngredients = new Ingredient[sis.size()];
        selectedIngredients = sis.toArray(selectedIngredients);

        return selectedIngredients;
    }

    public Portion getSelectedPortion() {
        for (int i = 0; i < portions.length; i++) {
            if (portions[i].isChecked()) return portions[i];
        }
        return portions[0];
    }

    public int getSelectedPortionIndex() {
        for (int i = 0; i < portions.length; i++) {
            if (portions[i].isChecked()) return i;
        }
        return 0;
    }

    public void setSelectedIngredients(Ingredient[] selectedIngredients) {
        for (int i = 0; i < ingredients.length; i++) {
            for (int j = 0; j < selectedIngredients.length; j++) {
                if (ingredients[i].getName().equalsIgnoreCase(selectedIngredients[j].getName())) {
                    ingredients[i].setCount(selectedIngredients[j].getCount());
                }
            }
        }
    }

    public void setPortion(Portion portion) {
        this.portions = new Portion[] {portion};
    }

    public void setSelectedPortion(Portion selectedPortion) {
        this.deselectAllPortions();
        for (Portion portion : portions) {
            if (portion.getName().equalsIgnoreCase(selectedPortion.getName())) {
                portion.setChecked(true);
            }
        }
    }

    public void setSelectedPortionByName(String name) {
        this.deselectAllPortions();
        for (Portion portion : portions) {
            if (portion.getName().equalsIgnoreCase(name)) {
                portion.setChecked(true);
            }
        }
    }

    public void setSelectedPortionByIndex(int index) {
        this.deselectAllPortions();
        portions[index].setChecked(true);
    }

    private void deselectAllPortions() {
        for (Portion portion : portions) {
            portion.setChecked(false);
        }
    }

    public int getSelectedOrDefaultPortionPosition() {
        for (int i = 0; i < portions.length; i++) {
            if (portions[i].isChecked()) return i;
        }
        return 0;
    }

    public double getTotalSelectedSum() {
        double mainOptionCost = this.getSelectedPortion().getPrice() * (double) this.getCount();
        double ingredientsCost = 0.0;
        for (Ingredient ingredient : this.getSelectedIngredients()) {
            ingredientsCost += ingredient.getPrice() * (double) ingredient.getCount();
        }
        return mainOptionCost + ingredientsCost;
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
