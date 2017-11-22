package ru.arink_group.deliveryapp.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderIngredientDTO {

    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("total_cost")
    @Expose
    private Double totalCost;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
