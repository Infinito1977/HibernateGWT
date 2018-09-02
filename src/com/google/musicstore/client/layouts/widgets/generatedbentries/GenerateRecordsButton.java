package com.google.musicstore.client.layouts.widgets.generatedbentries;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.musicstore.client.MusicStoreServiceAsync;
import com.google.musicstore.client.dto.RecordDTO;
import com.google.musicstore.client.layouts.sub.BlockRecordsPanel;

public class GenerateRecordsButton extends Button {
    private static Logger logger;

    public GenerateRecordsButton(final MusicStoreServiceAsync musicStoreService, final TextBox recordCountTB,
	    Logger parentLogger) {
	super("Generate");
	logger = Logger.getLogger(this.getClass().getName());
	logger.setParent(parentLogger);
	addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		logger.info("GenerateRecordsButton clicked");
		final BlockRecordsPanel blockRecordsPanel = new BlockRecordsPanel("Saving records", logger);
		int recordCount = new Integer(recordCountTB.getValue());
		RecordDTO[] recordsDTO = new RecordDTO[recordCount];
		int maxLength = new Integer(recordCount).toString().length();
		for (int i = 0; i < recordCount; i++) {
		    RecordDTO record = new RecordDTO();

		    String recordTitle = "Record";
		    int length = new Integer(i + 1).toString().length();
		    for (; length < maxLength; length++) {
			recordTitle += "0";
		    }
		    recordTitle += new Integer(i + 1);
		    record.setTitle(recordTitle);

		    record.setYear(1999);
		    record.setPrice(1);
		    recordsDTO[i] = record;
		}
		musicStoreService.saveRecords(recordsDTO, new AsyncCallback<Void>() {
		    @Override
		    public void onFailure(Throwable caught) {
			blockRecordsPanel.setVisible(false);
			Window.alert("Failed to save records.");
			logger.severe(caught.getLocalizedMessage());
		    }

		    @Override
		    public void onSuccess(Void result) {
			blockRecordsPanel.setVisible(false);
			Window.alert(recordCountTB.getValue() + " records succesfully saved");
			logger.info(recordCountTB.getValue() + " records succesfully saved");
		    }
		});
	    }
	});
	logger.finest("GenerateRecordsButton initialized");
    }
}
