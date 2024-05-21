package com.example.jewelryecommerceapp.Models;

import android.annotation.SuppressLint;
import android.os.Build;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Notice implements Serializable {
public boolean isSend=false;
    String titleNotice;
    String contentNotice;

static int numId=0;

    @SuppressLint("NewApi")
    public Notice( String titleNotice,String contentNotice) {

        this.titleNotice = titleNotice;
        this.contentNotice= contentNotice;


    }
    public Notice(){

    }

    public String getContentNotice() {
        return contentNotice;
    }

    public void setContentNotice(String contentNotice) {
        this.contentNotice = contentNotice;
    }


    public String getTitleNotice() {
        return titleNotice;
    }

    public void setTitleNotice(String titleNotice) {
        this.titleNotice = titleNotice;
    }
}
