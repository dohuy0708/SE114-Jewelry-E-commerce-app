package com.example.jewelryecommerceapp.Interfaces;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProvinceResponse {
    @SerializedName("results")
    private List<Province> results;

    public List<Province> getResults() {
        return results;
    }

    public void setResults(List<Province> results) {
        this.results = results;
    }
}
