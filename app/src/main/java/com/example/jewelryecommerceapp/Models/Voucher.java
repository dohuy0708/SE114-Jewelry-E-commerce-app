package com.example.jewelryecommerceapp.Models;

import java.time.LocalDate;
import java.util.Date;

public class Voucher {
    private String ID;
    private String code;
    private String content;
    private int discount;
    private Date DateBegin;
    private  Date DateEnd;

    public  Voucher(String chaomung30T4, double v, LocalDate sDate, LocalDate eDate)
    {

    }
    public Voucher(String Code, String content, int discount, Date datebegin, Date dateend)
    {
        this.code = code;
        this.content = content;
        this.DateBegin = datebegin;
        this.DateEnd = dateend;
        this.discount = discount;
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
}
