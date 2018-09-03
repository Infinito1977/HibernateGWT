package com.google.musicstore.domain;

import java.io.Serializable;

import com.google.musicstore.client.dto.LogLevelDTO;

public class LogLevel implements Serializable {
    private static final long serialVersionUID = 1344632806748630863L;
    private String value;
    
    public LogLevel() {}
    
    public LogLevel(LogLevelDTO logLevelDTO) {
	value = logLevelDTO.getLogLevel();
    }
    
    public String getValue() {
	return value;
    }

    public void setValue(String logLevel) {
	value = logLevel;
    }
}
