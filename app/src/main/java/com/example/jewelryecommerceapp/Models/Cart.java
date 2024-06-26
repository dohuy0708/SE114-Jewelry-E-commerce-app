package com.example.jewelryecommerceapp.Models;

import java.util.ArrayList;

public class Cart {
    private String cartId;
    private int totalAmount;
    private int totalPrice;
    private String userId;

    private ArrayList<CartProducts> cartProducts;

    public Cart(String cartId, int totalAmount, int totalPrice, String userId, ArrayList<CartProducts> CartProducts) {
        this.cartId = cartId;
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.cartProducts=CartProducts;
    }

    public Cart() {
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<CartProducts> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(ArrayList<CartProducts> cartProducts) {
        this.cartProducts = cartProducts;
    }
}

