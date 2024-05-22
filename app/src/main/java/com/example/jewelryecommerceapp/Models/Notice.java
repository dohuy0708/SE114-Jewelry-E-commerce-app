package com.example.jewelryecommerceapp.Models;

import android.annotation.SuppressLint;
import android.os.Build;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Notice implements Serializable {
    String titleNotice;
    String contentNotice;
    String date;

    @SuppressLint("NewApi")
    public Notice( String titleNotice,String contentNotice,String date) {

        this.titleNotice = titleNotice;
        this.contentNotice= contentNotice;
        this.date=date;

    }
    public Notice(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
