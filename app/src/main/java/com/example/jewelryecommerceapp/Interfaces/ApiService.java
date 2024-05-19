package com.example.jewelryecommerceapp.Interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/api/province")
    Call<ProvinceResponse> getProvinces();
    @GET("/api/province/district/{provinceId}")
    Call<DistrictResponse> getDistrictsByProvince(@Path("provinceId") int provinceId);
    @GET("/api/province/ward/{districtId}")
    Call<WardResponse> getWardsByDistrict(@Path("districtId") int districtId);
}
