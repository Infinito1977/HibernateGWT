package com.google.musicstore.client.layouts.widgets.generatedbentries;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.musicstore.client.MusicStoreServiceAsync;

public class DeleteAllAccountsButton extends Button {
    public DeleteAllAccountsButton(final MusicStoreServiceAsync musicStoreService) {
	super("Delete All");
	addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		musicStoreService.deleteAccounts(new AsyncCallback<Integer>() {
		    @Override
		    public void onFailure(Throwable caught) {
			Window.alert("Failed to delete accounts.");
		    }

		    @Override
		    public void onSuccess(Integer result) {
			Window.alert(result + " accounts succesfully deleted");
		    }
		});
	    }
	});
    }
}
