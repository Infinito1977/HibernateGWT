package com.google.musicstore.client.layouts.sub;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Label;

public class BlockRecordsPanel extends BlockPanel {
    private static Logger logger;

    public BlockRecordsPanel(String text, Logger parentLogger) {
	super(20, parentLogger);
	logger = Logger.getLogger(this.getClass().getName());
	logger.setParent(parentLogger);
	setWidget(new Label(text));
	logger.finest("BlockRecordsPanel initialized with custom text");
    }

    public BlockRecordsPanel(Logger parentLogger) {
	this("Retrieving records...", parentLogger);
	logger.finest("BlockRecordsPanel initialized");
    }
}
