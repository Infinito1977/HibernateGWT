package com.rieger.gwt.tutorials.hibernate.client.persistent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Account implements Serializable {
    private static final long serialVersionUID = 4014419077859640700L;
    private Long id;
    private String name;
    private String password;
    private Set<Record> records;

    public Account() {}

    public Account(Long id) {
	this.setId(id);
    }

    public void addRecord(Record record) {
	if (records == null) {
	    records = new HashSet<Record>();
	}
	records.add(record);
    }

    public void removeRecord(Record record) {
	if (records == null) {
	    return;
	}
	records.remove(record);
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }
}
