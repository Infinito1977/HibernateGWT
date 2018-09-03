package com.google.musicstore.client.layouts;

import java.util.List;
import java.util.logging.Logger;

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
import com.google.musicstore.client.layouts.sub.BlockAccountsPanel;
import com.google.musicstore.client.layouts.sub.BlockRecordsPanel;

public class AddRecordsToAccountPanel extends HorizontalPanel {
    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Constructs the records to account panel widgets and adds them to the panel.
     * 
     * @param addRecordsToAccountPanel
     *            the panel to construct
     * @param musicStoreService
     *            a handle to the music store service
     */
    public AddRecordsToAccountPanel(final MusicStoreServiceAsync musicStoreService, String width, Logger parentLogger) {
	logger.setParent(parentLogger);
	setSize(width, "500px");
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
		logger.fine("<<Add Records To Account>>: User clicked <Add Record>");
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
	logger.finer("Panel <<Add Records To Accounts>> initialized");
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
	final BlockAccountsPanel blockPanel = new BlockAccountsPanel(logger);
	musicStoreService.getAccounts(new AsyncCallback<List<AccountDTO>>() {
	    @Override
	    public void onFailure(Throwable caught) {
		blockPanel.setVisible(false);
		Window.alert("Failed to retrieve accounts.");
		logger.severe(caught.getLocalizedMessage());
	    }

	    @Override
	    public void onSuccess(List<AccountDTO> result) {
		ListBox accountIds = (ListBox) getWidget(1);
		accountIds.clear();
		for (AccountDTO account : result) {
		    accountIds.addItem(String.valueOf(account.getName()), String.valueOf(account.getId()));
		}
		blockPanel.setVisible(false);
		logger.info(result.size() + " accounts loaded");
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
	final BlockRecordsPanel blockPanel = new BlockRecordsPanel(logger);
	musicStoreService.getRecords(new AsyncCallback<List<RecordDTO>>() {
	    @Override
	    public void onFailure(Throwable caught) {
		blockPanel.setVisible(false);
		Window.alert("Failed to retrieve records.");
		logger.severe(caught.getLocalizedMessage());
	    }

	    @Override
	    public void onSuccess(List<RecordDTO> result) {
		ListBox recordTitles = (ListBox) getWidget(3);
		recordTitles.clear();
//		Collections.sort(result);
		for (RecordDTO record : result) {
		    recordTitles.addItem(record.getTitle(), String.valueOf(record.getId()));
		}
		blockPanel.setVisible(false);
		logger.info(result.size() + " records loaded");
	    }
	});
    }
}
