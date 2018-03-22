package ru.arink_group.deliveryapp.domain.dto;

/**
 * Created by kirillvs on 20.10.17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceDTO {

    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("device_type")
    @Expose
    private String deviceType;
    @SerializedName("registration_token")
    @Expose
    private String registrationToken;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }
}
