package com.google.musicstore.client.layouts.sub;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Label;

public class BlockAccountsPanel extends BlockPanel {
    private static Logger logger;

    public BlockAccountsPanel(String text, Logger parentLogger) {
	super(-20, parentLogger);
	logger = Logger.getLogger(this.getClass().getName());
	logger.setParent(parentLogger);
	setWidget(new Label(text));
	logger.finest("BlockAccountsPanel initialized with custom text");
    }

    public BlockAccountsPanel(Logger parentLogger) {
	this("Retrieving accounts...", parentLogger);
	logger.finest("BlockAccountsPanel initialized");
    }
}
