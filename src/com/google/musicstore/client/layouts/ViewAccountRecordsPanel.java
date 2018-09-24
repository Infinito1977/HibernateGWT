package com.google.musicstore.client.layouts;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.musicstore.client.MusicStoreServiceAsync;
import com.google.musicstore.client.model.AccountDTO;
import com.google.musicstore.client.model.RecordDTO;

/**
 * Constructs the view account / records panel widgets and adds them to the panel.
 * 
 * @param viewAccountRecordsPanel
 *            the panel to construct
 * @param musicStoreService
 *            a handle to the music store service
 */
public class ViewAccountRecordsPanel extends VerticalPanel {
    private static Logger logger;

    public ViewAccountRecordsPanel(String width, Logger parentLogger) {
	logger = Logger.getLogger(this.getClass().getName());
	logger.setParent(parentLogger);
	setSize(width, "500px");
	setBorderWidth(1);

	// Setup the account records table along with headers.
	final FlexTable accountRecords = new FlexTable();
	accountRecords.insertRow(0);
	accountRecords.setText(0, 0, "Account ID");
	accountRecords.setText(0, 1, "Account Name");
	accountRecords.setText(0, 2, "Record");
	accountRecords.setCellSpacing(10);
	accountRecords.setCellPadding(5);

	// Add the table and load all accounts and their records into it.
	add(accountRecords);
	logger.finer("Panel <<View Account Records>> initialized");
    }

    /**
     * Loads all accounts and their records and adds them to the view account record panel.
     * 
     * @param viewAccountRecordsPanel
     *            the panel to which the accounts and their corresponding records are added
     * @param musicStoreService
     *            a handle to the music store service
     */
    public void loadAccountRecords(final MusicStoreServiceAsync musicStoreService) {
	// Retrieve the account records table and populate it with account / record data.
	final FlexTable accountRecords = (FlexTable) getWidget(0);
	musicStoreService.getAccounts(new AsyncCallback<List<AccountDTO>>() {

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("Failed to get accounts and records.");
		logger.severe(caught.getLocalizedMessage());
	    }

	    @Override
	    public void onSuccess(List<AccountDTO> result) {
		if (result == null)
		    return;

		/*
		 * Fill the account / records table with accounts and their records. Clear all cells except the first row
		 * (reserved for headers) and only show the account id once with each record title listed under it. This could be
		 * improved to only show new entries, while keeping old entries in the table.
		 */
		for (int i = accountRecords.getRowCount() - 1; i > 0; i--) {
		    if (accountRecords.isCellPresent(i, 0)) {
			accountRecords.clearCell(i, 0);
			accountRecords.clearCell(i, 1);
			accountRecords.clearCell(i, 2);
		    } else {
			accountRecords.clearCell(i, 2);
		    }
		}

		int index = 1;
		for (AccountDTO account : result) {
		    Set<RecordDTO> records = account.getRecords();
		    boolean first = true;
		    for (RecordDTO record : records) {
			accountRecords.insertRow(index);

			if (first) {
			    accountRecords.setText(index, 0, String.valueOf(account.getId()));
			    accountRecords.setText(index, 1, account.getName());
			    first = false;
			}
			accountRecords.setText(index, 2, record.getTitle());
			index++;
		    }
		}
		logger.info(index - 1 + " accounts with records loaded");
	    }
	});
    }
}
