package com.google.musicstore.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.musicstore.client.MusicStoreService;
import com.google.musicstore.client.model.AccountDTO;
import com.google.musicstore.client.model.RecordDTO;
import com.google.musicstore.server.hibernate.HibernateModule;

public class MusicStoreServiceImpl extends RemoteServiceServlet implements MusicStoreService {
    private static final long serialVersionUID = -4825786932285819100L;
    
    public MusicStoreServiceImpl() {
	HibernateModule.initializeViaConfigFile();
    }

    @Override
    public List<AccountDTO> getAccounts() {
	return HibernateModule.getAccounts();
    }

    @Override
    public List<RecordDTO> getRecords() {
	return HibernateModule.getRecords();
    }

    @Override
    public Long saveAccount(AccountDTO accountDTO) {
	return HibernateModule.saveAccount(accountDTO);
    }

    @Override
    public void saveAccounts(AccountDTO[] accountsDTO) {
	HibernateModule.saveAccounts(accountsDTO);
    }

    @Override
    public Long saveRecord(RecordDTO recordDTO) {
	return HibernateModule.saveRecord(recordDTO);
    }

    @Override
    public void saveRecords(RecordDTO[] recordsDTO) {
	HibernateModule.saveRecords(recordsDTO);
    }

    @Override
    public int deleteAccounts() {
	return HibernateModule.deleteAccounts();
    }

    @Override
    public int deleteRecords() {
	return HibernateModule.deleteRecords();
    }

    @Override
    public int deleteAccountRecords() {
	return HibernateModule.deleteAccountRecords();
    }

    @Override
    public void saveRecordToAccount(AccountDTO accountDTO, RecordDTO recordDTO) {
	HibernateModule.saveRecordToAccount(accountDTO, recordDTO);
    }
}