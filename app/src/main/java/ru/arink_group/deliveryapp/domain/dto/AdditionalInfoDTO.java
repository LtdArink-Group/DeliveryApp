package ru.arink_group.deliveryapp.domain.dto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kirillvs on 20.10.17.
 */

public class AdditionalInfoDTO {

    @SerializedName("cost")
    @Expose
    private Double cost;
    @SerializedName("name")
    @Expose
    private String name;

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
