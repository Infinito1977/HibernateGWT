package com.google.musicstore.client.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Account version for RPC transfer that does not contain the Hibernate overhead
 * see http://www.gwtproject.org/articles/using_gwt_with_hibernate.html
 */
public class AccountDTO implements Serializable {
    private static final long serialVersionUID = 3694545196293905995L;
    private Long id;
    private String name;
    private String password;
    private Set<RecordDTO> records;

    public AccountDTO() {}

    public AccountDTO(Long id) {
	this.setId(id);
    }

    public AccountDTO(Long id, String name, String password, Set<RecordDTO> records) {
	this.setId(id);
	this.setName(name);
	this.setPassword(password);
	this.setRecords(records);
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

    public Set<RecordDTO> getRecords() {
	return records;
    }

    public void setRecords(Set<RecordDTO> records) {
	this.records = records;
    }
}
