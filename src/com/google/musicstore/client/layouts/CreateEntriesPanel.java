package com.google.musicstore.client.layouts;

import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateEntriesPanel extends VerticalPanel {
    public CreateEntriesPanel(VerticalPanel addAccountPanel, VerticalPanel addRecordPanel) {
	setSize("500px", "500px");
	setBorderWidth(1);
	add(addAccountPanel);
	add(addRecordPanel);
    }
}
