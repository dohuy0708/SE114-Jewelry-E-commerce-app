package com.example.jewelryecommerceapp.Models;

public class Comment {
    User user;
    String productID;
    String productType;
    int rate;
    String content;
    public Comment(){}
    public Comment(User user,String productID,String productType, int rate, String content) {
        this.user = user;
        this.productID=productID;
        this.productType=productType;
        this.rate = rate;
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProductID() {return productID;}

    public void setProductID(String productID) {this.productID = productID;}

    public String getProductType() {return productType;}

    public void setProductType(String productType) {this.productType = productType;}

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
