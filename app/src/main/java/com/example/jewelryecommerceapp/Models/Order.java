package com.example.jewelryecommerceapp.Models;

import java.util.ArrayList;

public class Order {
    private String OrderID;
    private String OrdererName;
    private String OrdererAdd;
    private String OrdererSDT;
    private int Money;
    private String Datee;
    private String Receive;

    private String status;
    private ArrayList<Product> ListPurchaseProduct;

    public Order(){

    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String Status){
        this.status=Status;
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
    public String getReceive() {
        return Receive;
    }

    public void setReceive(String receive) {
        Receive = receive;
    }
    public Order(String orderID, String ordererName, String ordererAdd, String ordererSDT, int money, String datee, String Status, String receive)
    {
        this.OrderID=orderID;
        this.OrdererName=ordererName;
        this.OrdererAdd=ordererAdd;
        this.OrdererSDT=ordererSDT;
        this.Money=money;
        this.Datee=datee;
        this.status=Status;
        this.Receive=receive;
    }
}
