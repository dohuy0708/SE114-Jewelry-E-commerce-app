package com.example.jewelryecommerceapp.Models;

public class Comment {
    User user;
    Product product;
    int rate;
    String content;

    public Comment(User user, Product product, int rate, String content) {
        this.user = user;
        this.product = product;
        this.rate = rate;
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
