package com.example.jewelryecommerceapp.Models;


import java.time.LocalDate;

public class Voucher {
    String name;
    double percent;
    LocalDate startDay;
    LocalDate endDay;

    public Voucher(String name, double percent, LocalDate startDay, LocalDate endDay) {
        this.name = name;
        this.percent = percent;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }
}
