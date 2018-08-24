package com.google.musicstore.client.layouts;

import com.google.gwt.user.client.ui.Label;

public class BlockAccountsPanel extends BlockPanel {
    public BlockAccountsPanel() {
	super(-20);
	setWidget(new Label("Retrieving accounts..."));
    }
}
