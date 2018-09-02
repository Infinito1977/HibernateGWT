package com.google.musicstore.client.layouts;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.musicstore.client.MusicStoreServiceAsync;
import com.google.musicstore.client.layouts.sub.AddAccountsSubPanel;
import com.google.musicstore.client.layouts.sub.AddRecordsSubPanel;

public class AddAccountsAndRecordsPanel extends VerticalPanel {
    private static Logger logger;

    public AddAccountsAndRecordsPanel(MusicStoreServiceAsync musicStoreService, String width, Logger parentLogger) {
	logger = Logger.getLogger(this.getClass().getName());
	logger.setParent(parentLogger);
	setSize(width, "500px");
	setBorderWidth(1);
	add(new AddAccountsSubPanel(musicStoreService, logger));
	add(new AddRecordsSubPanel(musicStoreService, logger));
	logger.finer("Panel <<Add Accounts/Records>> initialized");
    }
}
