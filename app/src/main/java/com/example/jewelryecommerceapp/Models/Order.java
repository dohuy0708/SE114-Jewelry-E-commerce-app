package com.example.jewelryecommerceapp.Models;

import java.util.ArrayList;

public class Order {

    private String OrderID;
    private int voucher;
    private int Money;
    private String Date;
    private String status;
    private Address address;
    private String UserID ;
    private ArrayList<CartItem> ListPurchaseProduct;

    public Order(){

    }
    public   Order(String ID, String UserID,int voucher, int money,String Date, String status,Address address,ArrayList<CartItem> Listproduct)
    {
        this.OrderID = ID;
        this.voucher = voucher;
        this.address = address;
        this.Date= Date;
        this.Money = money;
        this.UserID=UserID;
        this.status = status;
        this.ListPurchaseProduct= Listproduct;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public int getVoucher() {
        return voucher;
    }

    public void setVoucher(int voucher) {
        this.voucher = voucher;
    }

    public int getMoney() {
        return Money;
    }

    public void setMoney(int money) {
        Money = money;
    }

    public String getDatee() {
        return Date;
    }

    public void setDatee(String datee) {
        Date = datee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<CartItem> getListPurchaseProduct() {
        return ListPurchaseProduct;
    }

    public void setListPurchaseProduct(ArrayList<CartItem> listPurchaseProduct) {
        ListPurchaseProduct = listPurchaseProduct;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "OrderID='" + OrderID + '\'' +
                ", voucher=" + voucher +
                ", Money=" + Money +
                ", Date='" + Date + '\'' +
                ", status='" + status + '\'' +
                ", address=" + address +
                ", ListPurchaseProduct=" + ListPurchaseProduct +
                '}';
    }
}
