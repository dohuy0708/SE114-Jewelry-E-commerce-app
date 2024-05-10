package com.example.jewelryecommerceapp.Models;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;


public class CartProducts {

    public static final String COLLECTION_NAME ="Products in Cart";
    private Product Pros;
    public Spinner ProSize;
    public CheckBox ProChose;
    public EditText ProNumber;
    public Button IncrNumber;
    public Button DecrNumber;

    public Button Delete;

    public CartProducts(){

    }

    public CartProducts(Product pros, Spinner proSize, CheckBox proChose, EditText proNumber, Button incrNumber, Button decrNumber, Button delete)
    {
        this.Pros=pros;
        this.ProSize=proSize;
        this.ProChose=proChose;
        this.ProNumber=proNumber;
        this.IncrNumber=incrNumber;
        this.DecrNumber=decrNumber;
        this.Delete=delete;
    }

    public Product getPros(){
        return Pros;
    }
    public void setPros(Product pros)
    {
        this.Pros=pros;
    }

    public Spinner getProSize(){
        return ProSize;
    }
    public void setProSize(Spinner proSize)
    {
        this.ProSize=proSize;
    }

    public CheckBox getProChose(){
        return ProChose;
    }
    public void setProChose(CheckBox proChose)
    {
        this.ProChose=proChose;
    }

    public EditText getProNumber(){
        return ProNumber;
    }
    public void setProNumber(EditText proNumber)
    {
        this.ProNumber=proNumber;
    }
    public Button getIncrNumber(){
        return IncrNumber;
    }
    public void setIncrNumber(Button incrNumber)
    {
        this.IncrNumber=incrNumber;
    }

    public Button getDecrNumber(){
        return DecrNumber;
    }
    public void setDecrNumber(Button decrNumber)
    {
        this.DecrNumber=decrNumber;
    }

    public Button getDelete(){return Delete;}

    public void setDelete(Button delete) {
        this.Delete = delete;
    }
}
