package ru.arink_group.deliveryapp.domain.dto;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDTO {

    // for completed orders receiving

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("total_cost")
    @Expose
    private Double totalCost;
    @SerializedName("delivery_cost")
    @Expose
    private Double deliveryCost;
    @SerializedName("address_info")
    @Expose
    private OrderAddressInfoDTO addressInfo;
    @SerializedName("num")
    @Expose
    private int num;

    // for orders creation

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
    @SerializedName("device")
    @Expose
    private String device;
    @SerializedName("order_products")
    @Expose
    private List<OrderProductDTO> orderProducts = null;
    @SerializedName("note")
    @Expose
    private String note;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public OrderAddressInfoDTO getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(OrderAddressInfoDTO addressInfo) {
        this.addressInfo = addressInfo;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getNote() {return note;}

    public void setNote(String value) {this.note=value;}

}
