package com.google.musicstore.domain;

import java.io.Serializable;

public class Record implements Serializable {
    private static final long serialVersionUID = -6362169702509407309L;
    private Long id;
    private String title;
    private int year;
    private double price;

    public Record() {}

    public Record(Long id) {
	this.id = id;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public int getYear() {
	return year;
    }

    public void setYear(int year) {
	this.year = year;
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(double price) {
	this.price = price;
    }
}
