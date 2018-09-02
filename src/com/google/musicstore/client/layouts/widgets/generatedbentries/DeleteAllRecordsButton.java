package com.google.musicstore.client.layouts.widgets.generatedbentries;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.musicstore.client.MusicStoreServiceAsync;

public class DeleteAllRecordsButton extends Button {
    private static Logger logger;

    public DeleteAllRecordsButton(final MusicStoreServiceAsync musicStoreService, Logger parentLogger) {
	super("Delete All");
	logger = Logger.getLogger(this.getClass().getName());
	logger.setParent(parentLogger);
	addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		musicStoreService.deleteRecords(new AsyncCallback<Integer>() {
		    @Override
		    public void onFailure(Throwable caught) {
			Window.alert("Failed to delete records.");
			logger.severe(caught.getLocalizedMessage());
		    }

		    @Override
		    public void onSuccess(Integer result) {
			Window.alert(result + " records succesfully deleted");
			logger.info(result + " records succesfully deleted");
		    }
		});
	    }
	});
	logger.finest("DeleteAllRecordsButton initialized");
    }
}
