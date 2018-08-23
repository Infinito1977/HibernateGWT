package com.google.musicstore.client.layouts;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.musicstore.client.MusicStoreServiceAsync;
import com.google.musicstore.client.dto.AccountDTO;
import com.google.musicstore.client.dto.RecordDTO;

public class GenerateDBEntriesPanel extends FlexTable {
    private boolean failure = false;
    private final static int ACCOUNT_COUNT = 100;
    private final static int RECORD_COUNT = 1000;
    private final TextBox accountCountTB = new TextBox();
    private final TextBox recordCountTB = new TextBox();

    public GenerateDBEntriesPanel(final MusicStoreServiceAsync musicStoreService) {
	Button generateAccounts = new Button("Generate");
	generateAccounts.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		int accountCount = new Integer(accountCountTB.getValue());
		int maxLength = new Integer(accountCount).toString().length();
		for (int i = 0; i < accountCount; i++) {
		    if (failure)
			break;
		    AccountDTO account = new AccountDTO();
		    
		    String accountName = "Account";
		    int length = new Integer(i + 1).toString().length();
		    for (; length < maxLength; length++) {
			accountName += "0";
		    }
		    accountName += new Integer(i + 1);
		    account.setName(accountName);
		    
		    account.setPassword("");
		    musicStoreService.saveAccount(account, new AsyncCallback<Long>() {
			public void onFailure(Throwable caught) {
			    failure = true;
			    Window.alert("Failed to save accounts.");
			}

			@Override
			public void onSuccess(Long result) {}
		    });
		}
		Window.alert(accountCountTB.getValue() + " Accounts succesfully saved");
	    }
	});
	accountCountTB.setText(new Integer(ACCOUNT_COUNT).toString());
	Button deleteAllAccounts = new Button("Delete All");
	deleteAllAccounts.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		musicStoreService.deleteAccounts(new AsyncCallback<Integer>() {
		    @Override
		    public void onFailure(Throwable caught) {
			Window.alert("Failed to delete accounts.");
		    }

		    @Override
		    public void onSuccess(Integer result) {
			Window.alert(result + " Records succesfully saved");
		    }

		});
	    }
	});

	setWidget(0, 0, generateAccounts);
	setWidget(0, 1, accountCountTB);
	setText(0, 2, "Accounts");
	setWidget(0, 3, deleteAllAccounts);

	Button generateRecords = new Button("Generate");
	generateRecords.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		int recordCount = new Integer(recordCountTB.getValue());
		int maxLength = new Integer(recordCount).toString().length();
		for (int i = 0; i < new Integer(recordCountTB.getValue()); i++) {
		    if (failure)
			break;
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
		    musicStoreService.saveRecord(record, new AsyncCallback<Long>() {
			@Override
			public void onFailure(Throwable caught) {
			    failure = true;
			    Window.alert("Failed to save records.");
			}

			@Override
			public void onSuccess(Long result) {}

		    });
		}
		Window.alert(recordCountTB.getValue() + " Records succesfully saved");
	    }
	});
	recordCountTB.setText(new Integer(RECORD_COUNT).toString());
	Button deleteAllRecords = new Button("Delete All");
	deleteAllRecords.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		musicStoreService.deleteRecords(new AsyncCallback<Integer>() {
		    @Override
		    public void onFailure(Throwable caught) {
			Window.alert("Failed to delete records.");
		    }

		    @Override
		    public void onSuccess(Integer result) {
			Window.alert(result + " Records succesfully saved");
		    }

		});
	    }
	});
	setWidget(1, 0, generateRecords);
	setWidget(1, 1, recordCountTB);
	setText(1, 2, "Records");
	setWidget(1, 3, deleteAllRecords);
    }
}
