package com.example.jewelryecommerceapp.Models;

import android.graphics.Bitmap;

public class Customer {
    private Bitmap avatar;
    private int id;
    private String name;
    private String dateCreate;
    public Customer(Bitmap avatar,int id, String name, String dateCreate)
    {
        this.avatar = avatar;
        this.id = id;
        this.name = name;
        this.dateCreate = dateCreate;
    }
    public Customer(int id, String name, String dateCreate)
    {
        this.id = id;
        this.name = name;
        this.dateCreate = dateCreate;
    }
    public Bitmap getAvatar() {return avatar;};
    public int getId() {return id;}
    public String getName() {return name;}
    public String getDateCreate() {return dateCreate;}

    public void setAvatar(Bitmap avatar) {this.avatar = avatar;}
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setDateCreate(String dateCreate) {this.dateCreate = dateCreate;}
}
