package com.example.jewelryecommerceapp.Models;

public class Order {
    private String OrderID;
    private String OrdererName;
    private String OrdererAdd;
    private String OrdererSDT;
    private int Money;
    private String Datee;

    public Order(){

    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getOrdererName() {
        return OrdererName;
    }

    public void setOrdererName(String ordererName) {
        OrdererName = ordererName;
    }

    public String getOrdererAdd() {
        return OrdererAdd;
    }

    public void setOrdererAdd(String ordererAdd) {
        OrdererAdd = ordererAdd;
    }

    public String getOrdererSDT() {
        return OrdererSDT;
    }

    public void setOrdererSDT(String ordererSDT) {
        OrdererSDT = ordererSDT;
    }

    public int getMoney() {
        return Money;
    }

    public void setMoney(int money) {
        Money = money;
    }

    public String getDatee() {
        return Datee;
    }

    public void setDatee(String datee) {
        Datee = datee;
    }
    public Order(String orderID, String ordererName, String ordererAdd, String ordererSDT, int money, String datee)
    {
        this.OrderID=orderID;
        this.OrdererName=ordererName;
        this.OrdererAdd=ordererAdd;
        this.OrdererSDT=ordererSDT;
        this.Money=money;
        this.Datee=datee;
    }
}
