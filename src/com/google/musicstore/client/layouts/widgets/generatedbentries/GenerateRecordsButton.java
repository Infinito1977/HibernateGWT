package com.google.musicstore.client.layouts.widgets.generatedbentries;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.musicstore.client.MusicStoreServiceAsync;
import com.google.musicstore.client.dto.RecordDTO;

public class GenerateRecordsButton extends Button {
    public GenerateRecordsButton(final MusicStoreServiceAsync musicStoreService, final TextBox recordCountTB) {
	super("Generate");
	addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
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
			Window.alert("Failed to save records.");
		    }

		    @Override
		    public void onSuccess(Void result) {
			Window.alert(recordCountTB.getValue() + " records succesfully saved");
		    }
		});
	    }
	});
    }
}