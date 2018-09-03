package com.google.musicstore.client.layouts.sub;

import java.util.logging.Logger;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

public class BlockPanel extends PopupPanel {
    private static Logger logger;

    public BlockPanel(int offsetHeight, Logger parentLogger, String text) {
	logger = Logger.getLogger(this.getClass().getName());
	logger.setParent(parentLogger);
	int left = (int) ((Window.getClientWidth()) / 2.5);
	int top = (int) ((Window.getClientHeight()) / 2.5 - offsetHeight);
	setWidget(new Label(text));
	setPopupPosition(left, top);
	setGlassEnabled(true);
	show();
	logger.finest("BlockPanel initialized");
    }
}
