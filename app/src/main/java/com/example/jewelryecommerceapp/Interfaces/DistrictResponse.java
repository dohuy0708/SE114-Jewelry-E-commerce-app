package com.example.jewelryecommerceapp.Interfaces;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistrictResponse {
    @SerializedName("results")
    private List<District> results;

    public List<District> getResults() {
        return results;
    }

    public void setResults(List<District> results) {
        this.results = results;
    }
}
