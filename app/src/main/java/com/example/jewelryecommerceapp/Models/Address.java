package com.example.jewelryecommerceapp.Models;

import java.io.Serializable;

public class Address  implements Serializable {
    private String province, district, ward, street;
    public String addressId,detail,phoneNumber,fullName;
    public Address() {
    }

    public Address(String fullName, String province, String district, String ward, String detail, String phoneNumber) {
        this.fullName = fullName;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.detail = detail;
        this.phoneNumber = phoneNumber;
    }

    public String getProvince() {
        return province;
    }

    public String getDistrict() {
        return district;
    }

    public String getWard() {
        return ward;
    }
}
