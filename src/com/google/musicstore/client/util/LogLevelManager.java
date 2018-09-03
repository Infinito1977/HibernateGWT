package com.google.musicstore.client.util;

import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.musicstore.client.MusicStoreServiceAsync;
import com.google.musicstore.client.dto.LogLevelDTO;

public class LogLevelManager {
    public static void saveLogLevel(final LogLevelDTO logLevel, final Logger logger, MusicStoreServiceAsync musicStoreService) {
	musicStoreService.saveLogLevel(logLevel, new AsyncCallback<Void>() {
	    @Override
	    public void onFailure(Throwable caught) {
		logger.severe("Log level could not be saved: " + caught.getLocalizedMessage());
	    }

	    @Override
	    public void onSuccess(Void result) {
		logger.info("Log level " + logLevel + " saved");
	    }
	});

    }
}
