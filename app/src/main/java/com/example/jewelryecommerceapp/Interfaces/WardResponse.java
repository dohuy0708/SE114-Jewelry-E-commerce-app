package com.example.jewelryecommerceapp.Interfaces;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WardResponse {
    @SerializedName("results")
    private List<Ward> results;

    public List<Ward> getResults() {
        return results;
    }

    public void setResults(List<Ward> results) {
        this.results = results;
    }
}
