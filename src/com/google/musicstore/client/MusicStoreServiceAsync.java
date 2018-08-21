package com.google.musicstore.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.musicstore.domain.Account;
import com.google.musicstore.domain.Record;

public interface MusicStoreServiceAsync {
    public void getAccounts(AsyncCallback<List<Account>> callback);

    public void getRecords(AsyncCallback<List<Record>> callback);

    public void saveAccount(Account accountDTO, AsyncCallback<Long> callback);

    public void saveRecord(Record record, AsyncCallback<Long> callback);

    public void saveRecordToAccount(Account accountDTO, Record recordDTO, AsyncCallback<Void> callback);
}