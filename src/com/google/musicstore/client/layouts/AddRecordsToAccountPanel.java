package com.google.musicstore.client.layouts;

import java.util.Collections;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.musicstore.client.MusicStoreServiceAsync;
import com.google.musicstore.client.dto.AccountDTO;
import com.google.musicstore.client.dto.RecordDTO;

public class AddRecordsToAccountPanel extends HorizontalPanel {
    /**
     * Constructs the records to account panel widgets and adds them to the panel.
     * 
     * @param addRecordsToAccountPanel
     *            the panel to construct
     * @param musicStoreService
     *            a handle to the music store service
     */
    public AddRecordsToAccountPanel(final MusicStoreServiceAsync musicStoreService) {
	setSize("500px", "500px");
	setBorderWidth(1);

	// Setup and connect the record to account panel.
	Label acctId = new Label("Account:");
	final ListBox accountIds = new ListBox();
	Label recordTitle = new Label("Record Title:");
	final ListBox recordTitles = new ListBox();
	final Button addRecord = new Button("Add Record");

	// Size up list boxes.
	accountIds.setSize("100px", "35px");
	recordTitles.setSize("100px", "35px");

	add(acctId);
	add(accountIds);
	add(recordTitle);
	add(recordTitles);

	// Setup click handler to add selected record to selected account.
	addRecord.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		int accountIndex = accountIds.getSelectedIndex();
		int recordIndex = recordTitles.getSelectedIndex();
		Long accountId = new Long(accountIds.getValue(accountIndex));
		Long recordId = new Long(recordTitles.getValue(recordIndex));
		AccountDTO account = new AccountDTO(accountId);
		RecordDTO record = new RecordDTO(recordId);

		// Persist the record to the account.
		musicStoreService.saveRecordToAccount(account, record, new AsyncCallback<Void>() {
		    @Override
		    public void onFailure(Throwable caught) {
			Window.alert("Failed to save records to account.");
		    }

		    @Override
		    public void onSuccess(Void result) {
			Window.alert("Records saved to account.");
		    }

		});
	    }
	});
	add(addRecord);
    }

    /**
     * Loads all existing accounts and adds them to the records to account panel list box.
     * 
     * @param addRecordsToAccountPanel
     *            the panel to which we'll add the accounts
     * @param musicStoreService
     *            a handle to the music store service
     */
    public void loadAccounts(final MusicStoreServiceAsync musicStoreService) {
	musicStoreService.getAccounts(new AsyncCallback<List<AccountDTO>>() {
	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("Failed to retrieve accounts.");
	    }

	    @Override
	    public void onSuccess(List<AccountDTO> result) {
		ListBox accountIds = (ListBox) getWidget(1);
		accountIds.clear();
		for (AccountDTO account : result) {
		    accountIds.addItem(String.valueOf(account.getName()), String.valueOf(account.getId()));
		}
	    }
	});
    }

    /**
     * Loads all existing records and adds them to the records to account panel list box.
     * 
     * @param addRecordsToAccountPanel
     *            the panel to which the records will be added
     * @param musicStoreService
     *            a handle to the music store service
     */
    public void loadRecords(final MusicStoreServiceAsync musicStoreService) {
	musicStoreService.getRecords(new AsyncCallback<List<RecordDTO>>() {
	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("Failed to retrieve records.");
	    }

	    @Override
	    public void onSuccess(List<RecordDTO> result) {
		ListBox recordTitles = (ListBox) getWidget(3);
		recordTitles.clear();
		Collections.sort(result);
		for (RecordDTO record : result) {
		    recordTitles.addItem(record.getTitle(), String.valueOf(record.getId()));
		}
	    }
	});
    }
}