package com.a3.FruitMonthlyPriceMicroservice;

public class FruitMonthlyPrice {

    private String fruitName;
    private String monthName;
    private double price;
    private String environment;

    public FruitMonthlyPrice(String fruit, String month) {
        this.fruitName = fruit;
        this.monthName = month;
    }

    public FruitMonthlyPrice() {
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
}