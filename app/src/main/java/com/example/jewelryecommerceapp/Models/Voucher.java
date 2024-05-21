package com.example.jewelryecommerceapp.Models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

 public class Voucher {
    private String ID;
    private String code;
    private String content;
    private int discount;
    private int InCase;
    private String dateBegin; // Lưu trữ ngày dưới dạng chuỗi
    private String dateEnd;   // Lưu trữ ngày dưới dạng chuỗi

    // Constructors
    public Voucher(String ID, String code, String content, int discount, int InCase, String dateBegin, String dateEnd) {
        this.ID = ID;
        this.code = code;
        this.content = content;
        this.discount = discount;
        this.InCase = InCase;
        this.dateBegin = dateBegin ; // Chuyển đổi LocalDate thành chuỗi
        this.dateEnd = dateEnd ;     // Chuyển đổi LocalDate thành chuỗi
    }


    public Voucher() {}

    // Getters và setters

     public String getID() {
         return ID;
     }

     public void setID(String ID) {
         this.ID = ID;
     }

     public String getCode() {
         return code;
     }

     public void setCode(String code) {
         this.code = code;
     }

     public String getContent() {
         return content;
     }

     public void setContent(String content) {
         this.content = content;
     }

     public int getDiscount() {
         return discount;
     }

     public void setDiscount(int discount) {
         this.discount = discount;
     }

     public int getInCase() {
         return InCase;
     }

     public void setInCase(int inCase) {
         InCase = inCase;
     }

     public String getDateBegin() {
         return dateBegin;
     }

     public void setDateBegin(String dateBegin) {
         this.dateBegin = dateBegin;
     }

     public String getDateEnd() {
         return dateEnd;
     }

     public void setDateEnd(String dateEnd) {
         this.dateEnd = dateEnd;
     }
 }
