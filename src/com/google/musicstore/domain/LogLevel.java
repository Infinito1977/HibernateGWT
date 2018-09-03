package com.google.musicstore.domain;

import java.io.Serializable;

import com.google.musicstore.client.dto.LogLevelDTO;

public class LogLevel implements Serializable {
    private static final long serialVersionUID = 1344632806748630863L;
    private Long id;
    private String value;
    
    public LogLevel() {
	id = new Long(0);
    }
    
    public LogLevel(LogLevelDTO logLevelDTO) {
	this();
	value = logLevelDTO.getLogLevel();
    }
    
    public String getValue() {
	return value;
    }

    public void setValue(String logLevel) {
	value = logLevel;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
}
