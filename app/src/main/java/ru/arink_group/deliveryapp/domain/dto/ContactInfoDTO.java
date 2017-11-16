package ru.arink_group.deliveryapp.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactInfoDTO {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("web")
    @Expose
    private String web;
    @SerializedName("address")
    @Expose
    private String address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
