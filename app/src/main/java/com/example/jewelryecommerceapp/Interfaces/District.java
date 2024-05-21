package com.example.jewelryecommerceapp.Interfaces;
import com.google.gson.annotations.SerializedName;

public class District {
    @SerializedName("district_id")
    private int districtId;

    @SerializedName("district_name")
    private String districtName;

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
