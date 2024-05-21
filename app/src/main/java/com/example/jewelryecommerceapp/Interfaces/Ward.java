package com.example.jewelryecommerceapp.Interfaces;

import com.google.gson.annotations.SerializedName;

public class Ward {
    @SerializedName("ward_id")
    private int wardId;

    @SerializedName("ward_name")
    private String wardName;

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
}
