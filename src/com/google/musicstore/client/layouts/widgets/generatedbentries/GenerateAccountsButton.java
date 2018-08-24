package com.google.musicstore.client.layouts.widgets.generatedbentries;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.musicstore.client.MusicStoreServiceAsync;
import com.google.musicstore.client.dto.AccountDTO;
import com.google.musicstore.client.layouts.BlockAccountsPanel;

public class GenerateAccountsButton extends Button {

    public GenerateAccountsButton(final MusicStoreServiceAsync musicStoreService, final TextBox accountCountTB) {
	super("Generate");
	addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		final BlockAccountsPanel blockAccountsPanel = new BlockAccountsPanel("Saving accounts");
		int accountCount = new Integer(accountCountTB.getValue());
		AccountDTO[] accountsDTO = new AccountDTO[accountCount];
		int maxLength = new Integer(accountCount).toString().length();
		for (int i = 0; i < accountCount; i++) {
		    AccountDTO account = new AccountDTO();

		    String accountName = "Account";
		    int length = new Integer(i + 1).toString().length();
		    for (; length < maxLength; length++) {
			accountName += "0";
		    }
		    accountName += new Integer(i + 1);
		    account.setName(accountName);

		    account.setPassword("");
		    accountsDTO[i] = account;
		}
		musicStoreService.saveAccounts(accountsDTO, new AsyncCallback<Void>() {
		    public void onFailure(Throwable caught) {
			blockAccountsPanel.setVisible(false);
			Window.alert("Failed to save accounts.");
		    }

		    @Override
		    public void onSuccess(Void result) {
			blockAccountsPanel.setVisible(false);
			Window.alert(accountCountTB.getValue() + " accounts succesfully saved");
		    }
		});
	    }
	});
    }
}
