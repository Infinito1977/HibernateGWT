package com.google.musicstore.client.layouts;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.musicstore.client.MusicStoreServiceAsync;
import com.google.musicstore.client.layouts.widgets.generatedbentries.DeleteAllAccountRecordsButton;
import com.google.musicstore.client.layouts.widgets.generatedbentries.DeleteAllAccountsButton;
import com.google.musicstore.client.layouts.widgets.generatedbentries.DeleteAllRecordsButton;
import com.google.musicstore.client.layouts.widgets.generatedbentries.GenerateAccountsButton;
import com.google.musicstore.client.layouts.widgets.generatedbentries.GenerateRecordsButton;

public class GenerateDBEntriesPanel extends FlexTable {
    private static Logger logger;
    private final static int ACCOUNT_COUNT = 100;
    private final static int RECORD_COUNT = 1000;
    private final TextBox accountCountTB = new TextBox();
    private final TextBox recordCountTB = new TextBox();

    public GenerateDBEntriesPanel(final MusicStoreServiceAsync musicStoreService, Logger parentLogger) {
	logger = Logger.getLogger(this.getClass().getName());
	logger.setParent(parentLogger);
	// Generate and delete accounts
	accountCountTB.setText(new Integer(ACCOUNT_COUNT).toString());
	setWidget(0, 0, new GenerateAccountsButton(musicStoreService, accountCountTB));
	setWidget(0, 1, accountCountTB);
	setText(0, 2, "Accounts");
	setWidget(0, 3, new DeleteAllAccountsButton(musicStoreService));

	// Generate and delete records
	recordCountTB.setText(new Integer(RECORD_COUNT).toString());
	setWidget(1, 0, new GenerateRecordsButton(musicStoreService, recordCountTB));
	setWidget(1, 1, recordCountTB);
	setText(1, 2, "Records");
	setWidget(1, 3, new DeleteAllRecordsButton(musicStoreService));
	
	// Generate links between accounts and records or delete them again
	setWidget(2, 3, new DeleteAllAccountRecordsButton(musicStoreService));
	logger.finer("Panel <<Generate DB Entries>> initialized");
    }
}
