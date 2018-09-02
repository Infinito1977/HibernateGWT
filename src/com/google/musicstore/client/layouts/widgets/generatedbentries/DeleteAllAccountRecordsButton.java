package com.google.musicstore.client.layouts.widgets.generatedbentries;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.musicstore.client.MusicStoreServiceAsync;

public class DeleteAllAccountRecordsButton extends Button {
    private static Logger logger;

    public DeleteAllAccountRecordsButton(final MusicStoreServiceAsync musicStoreService, Logger parentLogger) {
	super("Delete All Account Records");
	logger = Logger.getLogger(this.getClass().getName());
	logger.setParent(parentLogger);
	addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		logger.info("DeleteAllAccountRecordsButton clicked");
		musicStoreService.deleteAccountRecords(new AsyncCallback<Integer>() {
		    @Override
		    public void onFailure(Throwable caught) {
			Window.alert("Failed to delete accounts.");
			logger.severe(caught.getLocalizedMessage());
		    }

		    @Override
		    public void onSuccess(Integer result) {
			Window.alert(result + " accounts records succesfully deleted");
			logger.info(result + " accounts records succesfully deleted");
		    }
		});
	    }
	});
	logger.finest("DeleteAllAccountRecordsButton initialized");
    }
}
