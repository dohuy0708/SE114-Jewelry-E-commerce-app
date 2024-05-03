package com.example.jewelryecommerceapp.Models;

public class Service {
    String svId;
    int svImg;
    String svName;
    int svNumber;

    public Service(String svId, int svImg, String svName, int svNumber) {
        this.svId = svId;
        this.svImg = svImg;
        this.svName = svName;
        this.svNumber = svNumber;
    }

    public int getSvImg() {
        return svImg;
    }

    public void setSvImg(int svImg) {
        this.svImg = svImg;
    }

    public String getSvId() {
        return svId;
    }

    public void setSvId(String svId) {
        this.svId = svId;
    }

    public String getSvName() {
        return svName;
    }

    public void setSvName(String svName) {
        this.svName = svName;
    }

    public int getSvNumber() {
        return svNumber;
    }

    public void setSvNumber(int svNumber) {
        this.svNumber = svNumber;
    }
}
