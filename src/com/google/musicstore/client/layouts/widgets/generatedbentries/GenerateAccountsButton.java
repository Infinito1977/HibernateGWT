package com.google.musicstore.client.layouts.widgets.generatedbentries;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.musicstore.client.MusicStoreServiceAsync;
import com.google.musicstore.client.dto.AccountDTO;

public class GenerateAccountsButton extends Button {

    public GenerateAccountsButton(final MusicStoreServiceAsync musicStoreService, final TextBox accountCountTB) {
	super("Generate");
	addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
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
			Window.alert("Failed to save accounts.");
		    }

		    @Override
		    public void onSuccess(Void result) {
			Window.alert(accountCountTB.getValue() + " accounts succesfully saved");
		    }
		});
	    }
	});
    }

    public GenerateAccountsButton(SafeHtml html) {
	super(html);
	// TODO Auto-generated constructor stub
    }

    public GenerateAccountsButton(String html) {
	super(html);
	// TODO Auto-generated constructor stub
    }

    public GenerateAccountsButton(Element element) {
	super(element);
	// TODO Auto-generated constructor stub
    }

    public GenerateAccountsButton(SafeHtml html, ClickHandler handler) {
	super(html, handler);
	// TODO Auto-generated constructor stub
    }

    public GenerateAccountsButton(String html, ClickHandler handler) {
	super(html, handler);
	// TODO Auto-generated constructor stub
    }

}
