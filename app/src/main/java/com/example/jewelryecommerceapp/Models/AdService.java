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

    // Getter and setter for serviceType
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    // Getter and setter for serviceCode
    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    // Getter and setter for servicePrice
    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    // Getter and setter for serviceDescription
    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}
