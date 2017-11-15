package ru.arink_group.deliveryapp.domain.dao;

import java.io.Serializable;

/**
 * Created by kirillvs on 06.10.17.
 */

public class Ingredient implements Serializable{

    private String name;
    private String description;
    private double price;
    private int count;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
