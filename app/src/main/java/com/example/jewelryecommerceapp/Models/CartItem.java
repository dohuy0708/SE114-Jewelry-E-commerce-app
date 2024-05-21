package com.example.jewelryecommerceapp.Models;

public class CartItem {
    String ProductID;
    String ProductType;
    String ProductName;
    String UserID;
    int Amount;
    String Size;
    int isChoose;
    String image ;
    int ProductPrice;

    public CartItem()
    {

    }

    public CartItem(String UserID, String ProductID, String ProductType, int Amount, String Size,String image,String Name,int Price)
    {
        this.ProductID=ProductID;
        this.UserID=UserID;
        this.ProductType=ProductType;
        this.Amount = Amount;
        this.Size = Size;
        this.isChoose =1;
        this.image = image;
        this.ProductName = Name;
        this.ProductPrice = Price;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getSize() {
        return Size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setSize(String size) {
        Size = size;
    }

    public int getIsChoose() {
        return isChoose;
    }

    public void setIsChoose(int isChoose) {
        this.isChoose = isChoose;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(int productPrice) {
        ProductPrice = productPrice;
    }
}