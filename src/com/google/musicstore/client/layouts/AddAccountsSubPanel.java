package com.google.musicstore.client.layouts;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.musicstore.client.MusicStoreServiceAsync;
import com.google.musicstore.client.dto.AccountDTO;

public class AddAccountsSubPanel extends VerticalPanel {
    /**
     * Constructs the add account panel widgets and adds them to the panel.
     * 
     * @param createAccountPanel
     *            the panel to construct
     * @param musicStoreService
     *            a handle to the music store service
     */
    public AddAccountsSubPanel(final MusicStoreServiceAsync musicStoreService) {
	setSize("500px", "200px");
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
	add(acctName);
	add(accountName);
	add(acctPassword);
	add(accountPassword);
	add(addAccount);
    }
}
