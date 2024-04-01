package com.example.jewelryecommerceapp.Models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class District {
    private String DisName;
    private int DisID;
    private ArrayList<Ward> wards;

    public String getDisName(){
        return DisName;
    }
    public void setDIsName(String DisName ){
        this.DisName=DisName;
    }

    public int getDisID(){
        return DisID;
    }
    public void setDisID(int DisID){
        this.DisID=DisID;
    }

    public ArrayList<Ward> getWards(){
        return wards;
    }
}
