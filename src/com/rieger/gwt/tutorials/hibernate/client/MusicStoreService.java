package com.rieger.gwt.tutorials.hibernate.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rieger.gwt.tutorials.hibernate.client.persistent.Account;
import com.rieger.gwt.tutorials.hibernate.client.persistent.Record;

@RemoteServiceRelativePath("musicservice")
public interface MusicStoreService extends RemoteService {
    public List<Account> getAccounts();

    public List<Record> getRecords();

    public Long saveAccount(Account account);

    public Long saveRecord(Record record);

    public void saveRecordToAccount(Account account, Record record);
}
