package ru.arink_group.deliveryapp.domain.dto;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderProductDTO {

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

}
