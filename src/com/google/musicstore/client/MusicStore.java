package com.google.musicstore.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.musicstore.client.layouts.AddAccountsSubPanel;
import com.google.musicstore.client.layouts.AddRecordsSubPanel;
import com.google.musicstore.client.layouts.AddRecordsToAccountPanel;
import com.google.musicstore.client.layouts.CreateEntriesPanel;
import com.google.musicstore.client.layouts.GenerateDBEntriesPanel;
import com.google.musicstore.client.layouts.ViewAccountRecordsPanel;

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
	 * Create the tab panel that will contain the three necessary views: (1) Add Accounts / Records: to add new accounts /
	 * records (2) Add Records To Account: to add existing records to existing accounts (3) View Account Records: to view
	 * existing accounts and their records.
	 */
	final TabPanel musicStorePanel = new TabPanel();

	// Create the music store RPC service interface to be used by all
	// components.
	final MusicStoreServiceAsync musicStoreService = (MusicStoreServiceAsync) GWT.create(MusicStoreService.class);

	// Connect the creation panel pieces together, and attach to music store panel.
	musicStorePanel.add(
		new CreateEntriesPanel(new AddAccountsSubPanel(musicStoreService), new AddRecordsSubPanel(musicStoreService)),
		"Add Accounts/Records");

	// Create and setup the add records to account panel, and attach to music store panel.
	final AddRecordsToAccountPanel addRecordsToAccountPanel = new AddRecordsToAccountPanel(musicStoreService);
	musicStorePanel.add(addRecordsToAccountPanel, "Add Records To Account");

	// Create and setup the view account records panel, and attach to music store panel.
	final ViewAccountRecordsPanel viewAccountRecordsPanel = new ViewAccountRecordsPanel();
	musicStorePanel.add(viewAccountRecordsPanel, "View Account Records");

	// Add panel to generate DB entries
	musicStorePanel.add(new GenerateDBEntriesPanel(musicStoreService), "Generate DB Entries");

	/*
	 * When one of the tabs containing accounts or records to be displayed is selected, we have to load the new accounts /
	 * records for the appropriate tab. Tab 0: No records to display here. Tab 1: Load all accounts and records. Tab 2: Load
	 * all accounts and their records.
	 */
	musicStorePanel.addSelectionHandler(new SelectionHandler<Integer>() {
	    @Override
	    public void onSelection(SelectionEvent<Integer> event) {
		int selected = event.getSelectedItem();
		switch (selected) {
		case 0:
		    break;
		case 1:
		    addRecordsToAccountPanel.loadAccounts(musicStoreService);
		    addRecordsToAccountPanel.loadRecords(musicStoreService);
		    break;
		case 2:
		    viewAccountRecordsPanel.loadAccountRecords(musicStoreService);
		    break;
		}

	    }
	});

	// Select the first tab by default.
	musicStorePanel.selectTab(3);

	// Attach the music store panel to the page.
	RootPanel.get().add(musicStorePanel);
    }
}
