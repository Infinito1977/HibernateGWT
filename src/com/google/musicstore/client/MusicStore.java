package com.google.musicstore.client;

import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.musicstore.client.dto.AccountDTO;
import com.google.musicstore.client.dto.RecordDTO;

/**
 * A overly simplified music store interface to retrieve and view music store accounts and records using GWT RPC
 * services.
 */
public class MusicStore implements EntryPoint {

    /**
     * The standard entry point onModuleLoad() method.
     */
    public void onModuleLoad() {
	/*
	 * Create the tab panel that will contain the three necessary views: (1) Add Accounts / Records: to add new
	 * accounts / records (2) Add Records To Account: to add existing records to existing accounts (3) View Account
	 * Records: to view existing accounts and their records.
	 */
	final TabPanel musicStorePanel = new TabPanel();

	// Create and setup the creation panel to add accounts / records.
	final VerticalPanel createEntriesPanel = new VerticalPanel();
	final VerticalPanel addAccountPanel = new VerticalPanel();
	final VerticalPanel addRecordPanel = new VerticalPanel();

	createEntriesPanel.setSize("500px", "500px");
	createEntriesPanel.setBorderWidth(1);

	// Create the music store RPC service interface to be used by all
	// components.
	final MusicStoreServiceAsync musicStoreService = (MusicStoreServiceAsync) GWT.create(MusicStoreService.class);

	// Construct the Add Account / Record panels.
	constructAddAccountPanel(addAccountPanel, musicStoreService);
	constructAddRecordPanel(addRecordPanel, musicStoreService);

	// Connect the creation panel pieces together, and attach to music store
	// panel.
	createEntriesPanel.add(addAccountPanel);
	createEntriesPanel.add(addRecordPanel);
	musicStorePanel.add(createEntriesPanel, "Add Accounts/Records");

	// Create and setup the add records to account panel, and attach to music
	// store panel.
	final HorizontalPanel addRecordsToAccountPanel = new HorizontalPanel();
	constructRecordsToAccountPanel(addRecordsToAccountPanel, musicStoreService);
	musicStorePanel.add(addRecordsToAccountPanel, "Add Records To Account");

	// Create and setup the view account records panel, and attach to music
	// store panel.
	final VerticalPanel viewAccountRecordsPanel = new VerticalPanel();
	constructViewAccountRecordsPanel(viewAccountRecordsPanel, musicStoreService);
	musicStorePanel.add(viewAccountRecordsPanel, "View Account Records");

	/*
	 * When one of the tabs containing accounts or records to be displayed is selected, we have to load the new
	 * accounts / records for the appropriate tab. Tab 0: No records to display here. Tab 1: Load all accounts and
	 * records. Tab 2: Load all accounts and their records.
	 */
	musicStorePanel.addSelectionHandler(new SelectionHandler<Integer>() {
	    @Override
	    public void onSelection(SelectionEvent<Integer> event) {
		int selected = event.getSelectedItem();
		switch (selected) {
		case 0:
		    break;
		case 1:
		    loadAccounts(addRecordsToAccountPanel, musicStoreService);
		    loadRecords(addRecordsToAccountPanel, musicStoreService);
		    break;
		case 2:
		    loadAccountRecords(viewAccountRecordsPanel, musicStoreService);
		    break;
		}

	    }
	});

	// Select the first tab by default.
	musicStorePanel.selectTab(0);

	// Attach the music store panel to the page.
	RootPanel.get().add(musicStorePanel);
    }

    /**
     * Constructs the view account / records panel widgets and adds them to the panel.
     * 
     * @param viewAccountRecordsPanel
     *            the panel to construct
     * @param musicStoreService
     *            a handle to the music store service
     */
    private void constructViewAccountRecordsPanel(VerticalPanel viewAccountRecordsPanel,
	    final MusicStoreServiceAsync musicStoreService) {
	viewAccountRecordsPanel.setSize("500px", "500px");
	viewAccountRecordsPanel.setBorderWidth(1);

	// Setup the account records table along with headers.
	final FlexTable accountRecords = new FlexTable();
	accountRecords.insertRow(0);
	accountRecords.setText(0, 0, "Account ID");
	accountRecords.setText(0, 1, "Account Name");
	accountRecords.setText(0, 2, "Record");
	accountRecords.setCellSpacing(10);
	accountRecords.setCellPadding(5);

	// Add the table and load all accounts and their records into it.
	viewAccountRecordsPanel.add(accountRecords);
    }

    /**
     * Loads all accounts and their records and adds them to the view account record panel.
     * 
     * @param viewAccountRecordsPanel
     *            the panel to which the accounts and their corresponding records are added
     * @param musicStoreService
     *            a handle to the music store service
     */
    private void loadAccountRecords(VerticalPanel viewAccountRecordsPanel,
	    final MusicStoreServiceAsync musicStoreService) {

	// Retrieve the account records table and populate it with account / record
	// data.
	final FlexTable accountRecords = (FlexTable) viewAccountRecordsPanel.getWidget(0);
	musicStoreService.getAccounts(new AsyncCallback<List<AccountDTO>>() {

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("Failed to get accounts and records.");
	    }

	    @Override
	    public void onSuccess(List<AccountDTO> result) {
		if (result == null)
		    return;

		/*
		 * Fill the account / records table with accounts and their records. Clear all cells except the first
		 * row (reserved for headers) and only show the account id once with each record title listed under it.
		 * This could be improved to only show new entries, while keeping old entries in the table.
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
	    }
	});
    }

    /**
     * Constructs the records to account panel widgets and adds them to the panel.
     * 
     * @param addRecordsToAccountPanel
     *            the panel to construct
     * @param musicStoreService
     *            a handle to the music store service
     */
    private void constructRecordsToAccountPanel(HorizontalPanel addRecordsToAccountPanel,
	    final MusicStoreServiceAsync musicStoreService) {
	addRecordsToAccountPanel.setSize("500px", "500px");
	addRecordsToAccountPanel.setBorderWidth(1);

	// Setup and connect the record to account panel.
	Label acctId = new Label("Account Id:");
	final ListBox accountIds = new ListBox();
	Label recordTitle = new Label("Record Title:");
	final ListBox recordTitles = new ListBox();
	final Button addRecord = new Button("Add Record");

	// Size up list boxes.
	accountIds.setSize("100px", "35px");
	recordTitles.setSize("100px", "35px");

	addRecordsToAccountPanel.add(acctId);
	addRecordsToAccountPanel.add(accountIds);
	addRecordsToAccountPanel.add(recordTitle);
	addRecordsToAccountPanel.add(recordTitles);

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
	addRecordsToAccountPanel.add(addRecord);
    }

    /**
     * Loads all existing records and adds them to the records to account panel list box.
     * 
     * @param addRecordsToAccountPanel
     *            the panel to which the records will be added
     * @param musicStoreService
     *            a handle to the music store service
     */
    private void loadRecords(final HorizontalPanel addRecordsToAccountPanel,
	    final MusicStoreServiceAsync musicStoreService) {
	musicStoreService.getRecords(new AsyncCallback<List<RecordDTO>>() {
	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("Failed to retrieve records.");
	    }

	    @Override
	    public void onSuccess(List<RecordDTO> result) {
		ListBox recordTitles = (ListBox) addRecordsToAccountPanel.getWidget(3);
		recordTitles.clear();
		for (RecordDTO record : result) {
		    recordTitles.addItem(record.getTitle(), String.valueOf(record.getId()));
		}
	    }
	});
    }

    /**
     * Loads all existing accounts and adds them to the records to account panel list box.
     * 
     * @param addRecordsToAccountPanel
     *            the panel to which we'll add the accounts
     * @param musicStoreService
     *            a handle to the music store service
     */
    private void loadAccounts(final HorizontalPanel addRecordsToAccountPanel,
	    final MusicStoreServiceAsync musicStoreService) {
	musicStoreService.getAccounts(new AsyncCallback<List<AccountDTO>>() {
	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("Failed to retrieve accounts.");
	    }

	    @Override
	    public void onSuccess(List<AccountDTO> result) {
		ListBox accountIds = (ListBox) addRecordsToAccountPanel.getWidget(1);
		accountIds.clear();
		for (AccountDTO account : result) {
		    accountIds.addItem(String.valueOf(account.getId()), String.valueOf(account.getId()));
		}
	    }
	});
    }

    /**
     * Constructs the add record panel widgets and adds them to the panel.
     * 
     * @param createRecordPanel
     *            the panel to construct
     * @param musicStoreService
     *            a handle to the music store service
     */
    private void constructAddRecordPanel(VerticalPanel createRecordPanel,
	    final MusicStoreServiceAsync musicStoreService) {
	createRecordPanel.setSize("500px", "300px");
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
		    }

		    @Override
		    public void onSuccess(Long result) {
			Window.alert("Record saved");
		    }

		});
	    }

	});
	createRecordPanel.add(recTitle);
	createRecordPanel.add(recordTitle);
	createRecordPanel.add(recYear);
	createRecordPanel.add(recordYear);
	createRecordPanel.add(recPrice);
	createRecordPanel.add(recordPrice);
	createRecordPanel.add(addRecord);
    }

    /**
     * Constructs the add account panel widgets and adds them to the panel.
     * 
     * @param createAccountPanel
     *            the panel to construct
     * @param musicStoreService
     *            a handle to the music store service
     */
    private void constructAddAccountPanel(VerticalPanel createAccountPanel,
	    final MusicStoreServiceAsync musicStoreService) {
	createAccountPanel.setSize("500px", "200px");
	Label acctName = new Label("Account Name:");
	final TextBox accountName = new TextBox();
	Label acctPassword = new Label("Account Password:");
	final TextBox accountPassword = new TextBox();
	Button addAccount = new Button("Add Account");

	// Save the new account when the add account button is clicked.
	addAccount.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		AccountDTO account = new AccountDTO();
		account.setName(accountName.getText());
		account.setPassword(accountPassword.getText());
		musicStoreService.saveAccount(account, new AsyncCallback<Long>() {
		    public void onFailure(Throwable caught) {
			Window.alert("Failed to save account.");
		    }

		    public void onSuccess(Long result) {
			Window.alert("Account saved");
		    }
		});
	    }
	});
	createAccountPanel.add(acctName);
	createAccountPanel.add(accountName);
	createAccountPanel.add(acctPassword);
	createAccountPanel.add(accountPassword);
	createAccountPanel.add(addAccount);
    }

}
