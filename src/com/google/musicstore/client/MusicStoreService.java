package com.google.musicstore.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.musicstore.client.model.AccountDTO;
import com.google.musicstore.client.model.RecordDTO;

@RemoteServiceRelativePath("musicservice")
public interface MusicStoreService extends RemoteService {
    public List<AccountDTO> getAccounts();

    public List<RecordDTO> getRecords();

    public Long saveAccount(AccountDTO accountDTO);

    public void saveAccounts(AccountDTO[] accountsDTO);

    public Long saveRecord(RecordDTO recordDTO);

    public void saveRecords(RecordDTO[] recordDTOs);

    public int deleteAccounts();

    public int deleteRecords();
    
    public int deleteAccountRecords();

    public void saveRecordToAccount(AccountDTO accountDTO, RecordDTO recordDTO);
}
