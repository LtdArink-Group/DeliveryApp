package ru.arink_group.deliveryapp.domain.dto;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDTO {

    @SerializedName("company_id")
    @Expose
    private Integer companyId;
    @SerializedName("account_id")
    @Expose
    private String accountId;
    @SerializedName("address_id")
    @Expose
    private Integer addressId;
    @SerializedName("delivery_time")
    @Expose
    private String deliveryTime;
    @SerializedName("pickup")
    @Expose
    private Boolean pickup;
    @SerializedName("order_products")
    @Expose
    private List<OrderProductDTO> orderProducts = null;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Boolean getPickup() {
        return pickup;
    }

    public void setPickup(Boolean pickup) {
        this.pickup = pickup;
    }

    public List<OrderProductDTO> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductDTO> orderProducts) {
        this.orderProducts = orderProducts;
    }

}
