package com.google.musicstore.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.musicstore.client.model.AccountDTO;
import com.google.musicstore.client.model.RecordDTO;

public interface MusicStoreServiceAsync {
    public void getAccounts(AsyncCallback<List<AccountDTO>> callback);

    public void getRecords(AsyncCallback<List<RecordDTO>> callback);

    public void saveAccount(AccountDTO accountDTO, AsyncCallback<Long> callback);

    public void saveAccounts(AccountDTO[] accountsDTO, AsyncCallback<Void> callback);

    public void saveRecord(RecordDTO record, AsyncCallback<Long> callback);

    public void saveRecords(RecordDTO[] recordDTOs, AsyncCallback<Void> callback);

    public void deleteAccounts(AsyncCallback<Integer> callback);

    public void deleteRecords(AsyncCallback<Integer> callback);
    
    public void deleteAccountRecords(AsyncCallback<Integer> callback);

    public void saveRecordToAccount(AccountDTO accountDTO, RecordDTO recordDTO, AsyncCallback<Void> callback);
}