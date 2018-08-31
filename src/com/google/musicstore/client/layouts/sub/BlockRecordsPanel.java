package com.google.musicstore.client.layouts.sub;

import com.google.gwt.user.client.ui.Label;

public class BlockRecordsPanel extends BlockPanel {
    public BlockRecordsPanel() {
	this("Retrieving records...");
    }
    
    public BlockRecordsPanel(String text) {
	super(20);
	setWidget(new Label(text));
    }
}
