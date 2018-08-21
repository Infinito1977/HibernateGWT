package com.rieger.gwt.tutorials.hibernate.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rieger.gwt.tutorials.hibernate.client.persistent.Account;
import com.rieger.gwt.tutorials.hibernate.client.persistent.Record;

public interface MusicStoreServiceAsync {
    public void getAccounts(AsyncCallback<List<Account>> callback);

    public void getRecords(AsyncCallback<List<Record>> callback);

    public void saveAccount(Account accountDTO, AsyncCallback<Long> callback);

    public void saveRecord(Record record, AsyncCallback<Long> callback);

    public void saveRecordToAccount(Account accountDTO, Record recordDTO, AsyncCallback<Void> callback);
}
