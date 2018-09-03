package com.google.musicstore.client.dto;

import java.io.Serializable;
import java.util.logging.Level;

public class LogLevelDTO implements Serializable {
    private static final long serialVersionUID = 1344632806748630863L;
    private String logLevel;
    
    public LogLevelDTO() {}
    
    public LogLevelDTO(Level logLevel) {
	this.logLevel = logLevel.toString();
    }
    
    public String getLogLevel() {
	return logLevel;
    }

    public void setLogLevel(String logLevel) {
	this.logLevel = logLevel;
    }
}
