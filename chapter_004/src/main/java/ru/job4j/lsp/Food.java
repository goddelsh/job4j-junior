package ru.job4j.lsp;

import java.util.Calendar;
import java.util.Objects;

public abstract class Food {
    private String name;
    private Calendar createDate;
    private Calendar expireDate;
    private double price;
    private double disscount;

//    public Food(String name, Calendar createDate, Calendar expireDate, double price) {
//
//        if (Calendar.getInstance().getTimeInMillis() < createDate.getTimeInMillis() ||
//        expireDate.getTimeInMillis() < createDate.getTimeInMillis()) {
//            throw new IllegalArgumentException("Illegal date information");
//        }
//
//        this.name = name;
//        this.createDate = createDate;
//        this.expireDate = expireDate;
//        this.price = price;
//    }

    public double getPercent() {
        var timeLeft = Calendar.getInstance().getTimeInMillis() - createDate.getTimeInMillis();
        var timeLife = expireDate.getTimeInMillis() - createDate.getTimeInMillis();
        var result = (double) timeLeft / timeLife;
        return result;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public Calendar getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Calendar expireDate) {
        this.expireDate = expireDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDisscount() {
        return disscount;
    }

    public void setDisscount(double disscount) {
        this.disscount = disscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(name, food.name) &&
                Objects.equals(createDate, food.createDate) &&
                Objects.equals(expireDate, food.expireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, createDate, expireDate);
    }
}
