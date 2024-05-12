package com.example.jewelryecommerceapp.Models;

public class AdOrderLive {

    private String adOrderName;
    private String adOrderCode;
    private int adOrderSize;
    private int adOrderPrice;
    private String adOrderDate;
    private int adOrderNumber;

    public AdOrderLive() {
        this.adOrderCode=adOrderCode;
        this.adOrderDate=adOrderDate;
        this.adOrderName=adOrderName;
        this.adOrderNumber = adOrderNumber;
        this.adOrderSize = adOrderSize;
        this.adOrderPrice = adOrderPrice;
    }

    public String getAdOrderName (){
        return adOrderName;
    }

    public String getAdOrderCode (){
        return adOrderCode;
    }

    public int getAdOrderSize (){
        return adOrderSize;
    }

    public int getAdOrderPrice(){
        return adOrderPrice;
    }

    public String getAdOrderDate (){
        return adOrderDate;
    }

    public int getAdOrderNumber (){
        return adOrderNumber;
    }

    public void setAdOrderName (String adOrderName){
        this.adOrderName = adOrderName;
    }

    public void setAdOrderCode (String adOrderCode){
        this.adOrderCode = adOrderCode;
    }

    public void setAdOrderSize (int adOrderSize){
        this.adOrderSize = adOrderSize;
    }

    public void setAdOrderPrice (int adOrderPrice){
        this.adOrderPrice = adOrderPrice;
    }

    public void setAdOrderDate (String adOrderDate) {
        this.adOrderDate = adOrderDate;
    }

    public void setAdOrderNumber (int adOrderNumber){
        this.adOrderNumber = adOrderNumber;
    }


}
