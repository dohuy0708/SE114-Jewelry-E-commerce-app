package com.example.jewelryecommerceapp.Models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Voucher {
    private String ID;
    private String code;
    private String content;
    private int discount;
    private int InCase;
    private LocalDate DateBegin;
    private LocalDate DateEnd;

    // DateTimeFormatter for converting between LocalDate and String


    public Voucher(String ID, String code, String content, int discount, int InCase, LocalDate DateBegin, LocalDate DateEnd) {
        this.ID = ID;
        this.code = code;
        this.content = content;
        this.discount = discount;
        this.InCase = InCase;
        this.DateBegin = DateBegin;
        this.DateEnd = DateEnd;
    }
    public Voucher(  String code, String content, int discount, int InCase, LocalDate DateBegin, LocalDate DateEnd) {

        this.code = code;
        this.content = content;
        this.discount = discount;
        this.InCase = InCase;
        this.DateBegin = DateBegin;
        this.DateEnd = DateEnd;
    }
    public Voucher()
    {

    }

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

    public LocalDate getDateBegin() {
        return DateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        DateBegin = dateBegin;
    }

    public LocalDate getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        DateEnd = dateEnd;
    }

    // Method để kiểm tra ngày hiện tại có nằm trong khoảng giữa DateBegin và DateEnd
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean isCurrentDateInRange(LocalDate date) {
        LocalDate currentDate = date;
        return (currentDate.isEqual(DateBegin) || currentDate.isAfter(DateBegin)) &&
                (currentDate.isEqual(DateEnd) || currentDate.isBefore(DateEnd));
    }
}