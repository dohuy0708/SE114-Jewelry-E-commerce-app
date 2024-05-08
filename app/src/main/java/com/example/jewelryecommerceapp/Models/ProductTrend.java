package com.example.jewelryecommerceapp.Models;

import java.util.ArrayList;

public class ProductTrend {

    private ArrayList<Product> ListTrendProduct;

    public ProductTrend()
    {

    }
    public ProductTrend(ArrayList<Product> list)
    {
        this.ListTrendProduct = list;
    }

    public ArrayList<Product> getListTrendProduct() {
        return ListTrendProduct;
    }

    public void setListTrendProduct(ArrayList<Product> listTrendProduct) {
        ListTrendProduct = listTrendProduct;
    }
}
