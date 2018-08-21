package com.rieger.gwt.tutorials.hibernate.client.persistent;

import java.io.Serializable;

public class Record implements Serializable {
    private static final long serialVersionUID = -2051403042898774916L;
    private Long id;
    private String title;
    private int year;
    private double price;

    public Record() {
    }

    public Record(Long id) {
      this.setId(id);
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
