package com.google.musicstore.client.dto;

import java.io.Serializable;
import java.util.logging.Level;

public class LogLevelDTO implements Serializable {
    private static final long serialVersionUID = 1344632806748630863L;
    private String value;
    
    public LogLevelDTO() {}
    
    public LogLevelDTO(Level logLevel) {
	value = logLevel.toString();
    }
    
    public String getValue() {
	return value;
    }

    public void setValue(String logLevel) {
	value = logLevel;
    }
    
    @Override
    public String toString() {
	return value;
    }
}
