package com.google.musicstore.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Account implements Serializable {
    private static final long serialVersionUID = 7973009858403497681L;
    private Long id;
    private String name;
    private String password;
    private Set<Record> records;

    public Account() {}

    public Account(Long id) {
	this.id = id;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Long getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Set<Record> getRecords() {
	return records;
    }

    public void setRecords(Set<Record> records) {
	this.records = records;
    }

    public void setId(Long id) {
	this.id = id;
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
}
