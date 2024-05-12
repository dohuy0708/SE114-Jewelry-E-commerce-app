package com.example.jewelryecommerceapp.Models;

public class AdService {
    private String serviceType;
    private String serviceCode;
    private int servicePrice;
    private String serviceDescription;
    // Constructor
    public AdService() {
        this.serviceType = serviceType;
        this.serviceCode = serviceCode;
        this.servicePrice = servicePrice;
        this.serviceDescription = serviceDescription;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }
    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}
