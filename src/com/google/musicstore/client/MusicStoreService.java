package com.google.musicstore.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.musicstore.domain.Account;
import com.google.musicstore.domain.Record;

@RemoteServiceRelativePath("musicservice")
public interface MusicStoreService extends RemoteService {
    public List<Account> getAccounts();

    public List<Record> getRecords();

    public Long saveAccount(Account account);

    public Long saveRecord(Record record);

    public void saveRecordToAccount(Account account, Record record);
}
