package com.example.jewelryecommerceapp.Models;

public class Notice {
    int imgNotice;
    String txtNotice;

    public Notice(int imgNotice, String txtNotice) {
        this.imgNotice = imgNotice;
        this.txtNotice = txtNotice;
    }

    public int getImgNotice() {
        return imgNotice;
    }

    public void setImgNotice(int imgNotice) {
        this.imgNotice = imgNotice;
    }

    public String getTxtNotice() {
        return txtNotice;
    }

    public void setTxtNotice(String txtNotice) {
        this.txtNotice = txtNotice;
    }
}
