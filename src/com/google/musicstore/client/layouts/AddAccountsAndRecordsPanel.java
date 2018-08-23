package com.google.musicstore.client.layouts;

import com.google.gwt.user.client.ui.VerticalPanel;

public class AddAccountsAndRecordsPanel extends VerticalPanel {
    public AddAccountsAndRecordsPanel(VerticalPanel addAccountPanel, VerticalPanel addRecordPanel) {
	setSize("650px", "500px");
	setBorderWidth(1);
	add(addAccountPanel);
	add(addRecordPanel);
    }
}
