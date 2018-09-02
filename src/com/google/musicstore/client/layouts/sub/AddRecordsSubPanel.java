package com.google.musicstore.client.layouts.sub;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.musicstore.client.MusicStoreServiceAsync;
import com.google.musicstore.client.dto.RecordDTO;

public class AddRecordsSubPanel extends VerticalPanel {
    private static Logger logger;

    /**
     * Constructs the add record panel widgets and adds them to the panel.
     * 
     * @param musicStoreService
     *            a handle to the music store service
     */
    public AddRecordsSubPanel(final MusicStoreServiceAsync musicStoreService, Logger parentLogger) {
	logger = Logger.getLogger(this.getClass().getName());
	logger.setParent(parentLogger);
	setHeight("300px");
	Label recTitle = new Label("Record Title:");
	final TextBox recordTitle = new TextBox();
	Label recYear = new Label("Record Year:");
	final TextBox recordYear = new TextBox();
	Label recPrice = new Label("Record Price:");
	final TextBox recordPrice = new TextBox();
	Button addRecord = new Button("Add Record");

	// Save the new record when the add record button is clicked.
	addRecord.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		RecordDTO record = new RecordDTO();
		record.setTitle(recordTitle.getText());
		record.setYear(Integer.valueOf(recordYear.getText()));
		record.setPrice(Double.valueOf(recordPrice.getText()));
		musicStoreService.saveRecord(record, new AsyncCallback<Long>() {
		    @Override
		    public void onFailure(Throwable caught) {
			Window.alert("Failed to save record.");
			logger.severe(caught.getLocalizedMessage());
		    }

		    @Override
		    public void onSuccess(Long result) {
			Window.alert("Record saved");
			logger.info("Record saved");
		    }

		});
	    }

	});
	add(recTitle);
	add(recordTitle);
	add(recYear);
	add(recordYear);
	add(recPrice);
	add(recordPrice);
	add(addRecord);
	logger.finest("Sub Panel <<Add Accounts/Records->Add Records>> initialized");
    }
}
