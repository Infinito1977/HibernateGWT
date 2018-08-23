package com.google.musicstore.client.dto;

import java.io.Serializable;

/**
 * Account version for RPC transfer that does not contain the Hibernate overhead
 * see http://www.gwtproject.org/articles/using_gwt_with_hibernate.html
 */
public class RecordDTO implements Comparable<RecordDTO>, Serializable {
    private static final long serialVersionUID = 4491572249945685435L;
    private Long id;
    private String title;
    private int year;
    private double price;

    public RecordDTO() {}

    public RecordDTO(Long id) {
	this.setId(id);
    }

    public RecordDTO(Long id, String title, int year, double price) {
	this.setId(id);
	this.setTitle(title);
	this.setYear(year);
	this.setPrice(price);
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

    @Override
    public int compareTo(RecordDTO o) {
	return title.compareTo(o.getTitle());
    }
}
