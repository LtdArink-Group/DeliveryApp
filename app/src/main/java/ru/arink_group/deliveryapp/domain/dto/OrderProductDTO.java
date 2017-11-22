package ru.arink_group.deliveryapp.domain.dto;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderProductDTO {

    // for orders receiving

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("product_title")
    @Expose
    private String productTitle;
    @SerializedName("total_cost")
    @Expose
    private Double totalCost;

    // for orders creation

    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("main_option")
    @Expose
    private String mainOption;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("ingredients")
    @Expose
    private List<OrderIngredientDTO> ingredients = null;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getMainOption() {
        return mainOption;
    }

    public void setMainOption(String mainOption) {
        this.mainOption = mainOption;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public List<OrderIngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<OrderIngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

}
