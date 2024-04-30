package com.example.jewelryecommerceapp.Models;

import android.annotation.SuppressLint;
import android.os.Build;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Notice implements Serializable {
    int imgNotice;
    String titleNotice;
    String contentNotice;
    LocalDateTime date;


    @SuppressLint("NewApi")
    public Notice(int imgNotice, String titleNotice) {
        this.imgNotice = imgNotice;
        this.titleNotice = titleNotice;
        if(date==null)

                date=LocalDateTime.now();

    }
    public Notice(){
        if(date==null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                date=LocalDateTime.now();
            }
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContentNotice() {
        return contentNotice;
    }

    public void setContentNotice(String contentNotice) {
        this.contentNotice = contentNotice;
    }

    public int getImgNotice() {
        return imgNotice;
    }

    public void setImgNotice(int imgNotice) {
        this.imgNotice = imgNotice;
    }

    public String getTitleNotice() {
        return titleNotice;
    }

    public void setTitleNotice(String titleNotice) {
        this.titleNotice = titleNotice;
    }
}
