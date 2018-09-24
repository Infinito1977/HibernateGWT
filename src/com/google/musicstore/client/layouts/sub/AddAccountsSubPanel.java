package com.google.musicstore.client.layouts.sub;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.musicstore.client.MusicStoreServiceAsync;
import com.google.musicstore.client.model.AccountDTO;

public class AddAccountsSubPanel extends VerticalPanel {
    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Constructs the add account panel widgets and adds them to the panel.
     * 
     * @param createAccountPanel
     *            the panel to construct
     * @param musicStoreService
     *            a handle to the music store service
     */
    public AddAccountsSubPanel(final MusicStoreServiceAsync musicStoreService, Logger parentLogger) {
	logger.setParent(parentLogger);
	setHeight("200px");
	Label acctName = new Label("Account Name:");
	final TextBox accountName = new TextBox();
	Label acctPassword = new Label("Account Password:");
	final TextBox accountPassword = new TextBox();
	Button addAccount = new Button("Add Account");

	// Save the new account when the add account button is clicked.
	addAccount.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		logger.fine("<<Add Accounts/Records>>: User clicked <Add Accounts>");
		AccountDTO account = new AccountDTO();
		account.setName(accountName.getText());
		account.setPassword(accountPassword.getText());
		musicStoreService.saveAccount(account, new AsyncCallback<Long>() {
		    public void onFailure(Throwable caught) {
			Window.alert("Failed to save account.");
			logger.severe(caught.getLocalizedMessage());
		    }

		    public void onSuccess(Long result) {
			Window.alert("Account saved");
			logger.info("Account saved");
		    }
		});
	    }
	});
	add(acctName);
	add(accountName);
	add(acctPassword);
	add(accountPassword);
	add(addAccount);
	logger.finest("Sub Panel <<Add Accounts/Records->Add Accounts>> initialized");
    }
}
