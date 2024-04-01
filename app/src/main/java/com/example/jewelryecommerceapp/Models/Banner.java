package com.example.jewelryecommerceapp.Models;

public class Banner {
    public static final String COLLECTION_NAME="banners";
    private String BanID;
    private String BanImage;

    public Banner(){

    }

    public String getBanID(){
        return BanID;
    }
    public void setBanID(String BanID) {
        this.BanID = BanID;
    }

    public String getBanImage(){
        return BanImage;
    }

    public void setBanImage(String BanImage) {
        this.BanImage=BanImage;
    }

    public Banner(String BanID, String BanImage){
        this.BanID=BanID;
        this.BanImage=BanImage;
    }

    @Override
    public String toString(){
        return "Banner{"+
                "BannerID="+BanID+'\''+
                ", BannerImage="+BanImage+'\''+
                '}';
    }
}
