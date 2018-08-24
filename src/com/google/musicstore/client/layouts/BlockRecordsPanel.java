package com.google.musicstore.client.layouts;

import com.google.gwt.user.client.ui.Label;

public class BlockRecordsPanel extends BlockPanel {
    public BlockRecordsPanel() {
	super(20);
	setWidget(new Label("Retrieving records..."));
    }
}
