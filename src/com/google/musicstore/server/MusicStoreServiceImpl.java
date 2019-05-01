package com.google.musicstore.server;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.musicstore.client.MusicStoreService;
import com.google.musicstore.client.model.AccountDTO;
import com.google.musicstore.client.model.RecordDTO;
import com.google.musicstore.server.hibernate.HibernateModule;

public class MusicStoreServiceImpl extends RemoteServiceServlet implements MusicStoreService {
    private static final long serialVersionUID = -4825786932285819100L;
    private static final Logger LOGGER = LogManager.getLogger(MusicStoreServiceImpl.class.getName());
    
    public MusicStoreServiceImpl() {
	HibernateModule.initializeViaConfigFile();
	LOGGER.info("Hibernate initialized via config file");
    }

    @Override
    public List<AccountDTO> getAccounts() {
	LOGGER.info("getAccounts");
	return HibernateModule.getAccounts();
    }

    @Override
    public List<RecordDTO> getRecords() {
	LOGGER.info("getRecords");
	return HibernateModule.getRecords();
    }

    @Override
    public Long saveAccount(AccountDTO accountDTO) {
	LOGGER.info("saveAccount");
	return HibernateModule.saveAccount(accountDTO);
    }

    @Override
    public void saveAccounts(AccountDTO[] accountsDTO) {
	LOGGER.info("saveAccounts");
	HibernateModule.saveAccounts(accountsDTO);
    }

    @Override
    public Long saveRecord(RecordDTO recordDTO) {
	LOGGER.info("saveRecord");
	return HibernateModule.saveRecord(recordDTO);
    }

    @Override
    public void saveRecords(RecordDTO[] recordsDTO) {
	LOGGER.info("saveRecords");
	HibernateModule.saveRecords(recordsDTO);
    }

    @Override
    public int deleteAccounts() {
	LOGGER.info("deleteAccounts");
	return HibernateModule.deleteAccounts();
    }

    @Override
    public int deleteRecords() {
	LOGGER.info("deleteRecords");
	return HibernateModule.deleteRecords();
    }

    @Override
    public int deleteAccountRecords() {
	LOGGER.info("deleteAccountRecords");
	return HibernateModule.deleteAccountRecords();
    }

    @Override
    public void saveRecordToAccount(AccountDTO accountDTO, RecordDTO recordDTO) {
	LOGGER.info("saveRecordToAccount");
	HibernateModule.saveRecordToAccount(accountDTO, recordDTO);
    }
}