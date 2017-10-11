package ru.arink_group.deliveryapp.domain;

/**
 * Created by kirillvs on 09.10.17.
 */

public class SelectedPortion {

    private int id;

    private String name;

    private String description;

    private float price;

    private String size;

    private int count;

    public SelectedPortion() {

    }

    public SelectedPortion(Portion portion) {
        this.id = portion.getId();
        this.name = portion.getName();
        this.description = portion.getDescription();
        this.price = Float.parseFloat(portion.getPrice());
        this.size = portion.getSize();
        this.count = portion.getCount();
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
