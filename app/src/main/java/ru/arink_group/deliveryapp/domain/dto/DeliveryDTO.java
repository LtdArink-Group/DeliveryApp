package ru.arink_group.deliveryapp.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryDTO {

    @SerializedName("cost")
    @Expose
    private Double cost;
    @SerializedName("period")
    @Expose
    private PeriodDTO period;
    @SerializedName("free_shipping")
    @Expose
    private Double freeShipping;
    @SerializedName("pickup_discount")
    @Expose
    private Double pickupDiscount;

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public PeriodDTO getPeriod() {
        return period;
    }

    public void setPeriod(PeriodDTO period) {
        this.period = period;
    }

    public Double getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(Double freeShipping) {
        this.freeShipping = freeShipping;
    }

    public Double getPickupDiscount() {
        return pickupDiscount;
    }

    public void setPickupDiscount(Double pickupDiscount) {
        this.pickupDiscount = pickupDiscount;
    }

}
