package com.example.jewelryecommerceapp.Models;

public class Customer {
    private Image avatar;
    private int id;
    private String name;
    private String dateCreate;
    public Customer(Image avatar,int id, String name, String dateCreate)
    {
        this.avatar = avatar;
        this.id = id;
        this.name = name;
        this.dateCreate = dateCreate;
    }
    public Image getAvatar() {return avatar;};
    public int getId() {return id;}
    public String getName() {return name;}
    public String getDateCreate() {return dateCreate;}

    public void setAvatar(Image avatar) {this.avatar = avatar;}
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setDateCreate(String dateCreate) {this.dateCreate = dateCreate;}
}
