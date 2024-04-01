package com.example.jewelryecommerceapp.Models;

import java.util.ArrayList;
public class Province {
    private String PrvName;
    private int PrvID;
    private ArrayList<District> districts;

    public String getPrvName(){
        return PrvName;
    }
    public void setPrvName(String PrvName ){
        this.PrvName=PrvName;
    }

    public int getPrvID(){
        return PrvID;
    }
    public void setPrvID(int PrvID){
        this.PrvID=PrvID;
    }

    public ArrayList<District> getDistricts(){
        return districts;
    }
}
