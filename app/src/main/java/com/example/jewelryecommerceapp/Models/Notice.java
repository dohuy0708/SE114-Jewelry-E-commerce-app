package com.example.jewelryecommerceapp.Models;

import android.annotation.SuppressLint;
import android.os.Build;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Notice implements Serializable {
    Object imgNotice;
    String titleNotice;
    String contentNotice;
    String id;
static int numId=0;

    @SuppressLint("NewApi")
    public Notice(Object imgNotice, String titleNotice,String contentNotice) {
        this.id=id;
        this.imgNotice = imgNotice;
        this.titleNotice = titleNotice;
        this.contentNotice= contentNotice;


        id=String.valueOf(numId+1);
        numId+=1;

    }
    public Notice(){

        id=String.valueOf(numId+1);
        numId+=1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getContentNotice() {
        return contentNotice;
    }

    public void setContentNotice(String contentNotice) {
        this.contentNotice = contentNotice;
    }

    public Object getImgNotice() {
        return imgNotice;
    }

    public void setImgNotice(Object imgNotice) {
        this.imgNotice = imgNotice;
    }

    public String getTitleNotice() {
        return titleNotice;
    }

    public void setTitleNotice(String titleNotice) {
        this.titleNotice = titleNotice;
    }
}
