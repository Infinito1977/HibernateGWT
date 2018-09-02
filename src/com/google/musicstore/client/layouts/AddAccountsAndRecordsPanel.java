package com.google.musicstore.client.layouts;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.VerticalPanel;

public class AddAccountsAndRecordsPanel extends VerticalPanel {
    private static Logger logger;

    public AddAccountsAndRecordsPanel(VerticalPanel addAccountPanel, VerticalPanel addRecordPanel, Logger parentLogger) {
	logger = Logger.getLogger(this.getClass().getName());
	logger.setParent(parentLogger);
	setSize("650px", "500px");
	setBorderWidth(1);
	add(addAccountPanel);
	add(addRecordPanel);
	logger.finer("Panel <<Add Accounts/Records>> initialized");
    }
}
