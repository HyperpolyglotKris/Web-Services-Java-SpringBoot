package com.a3.FruitTotalPriceMicroservice;

public class FruitTotalPrice {

    private String fruitName;
    private String monthName;
    private double price;
    private String environment;
    private int amount;
    private double totalPrice;

    public FruitTotalPrice() {
    }

    public String getFruit() {
        return fruitName;
    }

    public String getMonth() {
        return monthName;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setFruit(String fruit) {
        this.fruitName = fruit;
    }

    public void setMonth(String month) {
        this.monthName = month;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}