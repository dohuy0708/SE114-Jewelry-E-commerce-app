package com.example.jewelryecommerceapp.Interfaces;
import com.google.gson.annotations.SerializedName;

public class Province {
    @SerializedName("province_id")
    private int provinceId;

    @SerializedName("province_name")
    private String provinceName;

    @SerializedName("province_type")
    private String provinceType;

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceType() {
        return provinceType;
    }

    public void setProvinceType(String provinceType) {
        this.provinceType = provinceType;
    }
}

