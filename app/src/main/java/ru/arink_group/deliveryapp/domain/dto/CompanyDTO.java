package ru.arink_group.deliveryapp.domain.dto;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyDTO {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("categories")
    @Expose
    private List<Integer> categories = null;
    @SerializedName("addresses")
    @Expose
    private List<AddressDTO> addresses = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("contact_info")
    @Expose
    private ContactInfoDTO contactInfo;
    @SerializedName("delivery")
    @Expose
    private DeliveryDTO delivery;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("schedules")
    @Expose
    private List<CompanyWorkingDayDTO> workingDays;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ContactInfoDTO getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfoDTO contactInfo) {
        this.contactInfo = contactInfo;
    }

    public DeliveryDTO getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryDTO delivery) {
        this.delivery = delivery;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<CompanyWorkingDayDTO> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(List<CompanyWorkingDayDTO> workingDays) {
        this.workingDays = workingDays;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }
}
