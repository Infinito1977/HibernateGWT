package com.google.musicstore.server;

import java.util.List;

import com.google.musicstore.client.model.AccountDTO;
import com.google.musicstore.client.model.RecordDTO;
import com.google.musicstore.server.hibernate.HibernateModule;

import junit.framework.TestCase;

public class HibernateModuleTest extends TestCase {
    public void setUp() {
	HibernateModule.initializeHSQLDB();
    }
    
    public void testGet1Account() {
	HibernateModule.deleteAccounts();
	save1Account();
	List<AccountDTO> accounts = HibernateModule.getAccounts();
	assertEquals(1, accounts.size());
	HibernateModule.deleteAccounts();
    }

    public void testGet7Accounts() {
	int n = 7;
	HibernateModule.deleteAccounts();
	saveAccounts(n);
	List<AccountDTO> accounts = HibernateModule.getAccounts();
	assertEquals(n, accounts.size());
	HibernateModule.deleteAccounts();
    }

    public void testGet1Record() {
	HibernateModule.deleteRecords();
	save1Record();
	List<RecordDTO> records = HibernateModule.getRecords();
	assertEquals(1, records.size());
	HibernateModule.deleteRecords();
    }

    public void testGet5Records() {
	int n = 5;
	HibernateModule.deleteRecords();
	saveRecords(n);
	List<RecordDTO> records = HibernateModule.getRecords();
	assertEquals(n, records.size());
	HibernateModule.deleteRecords();
    }
    
    public void testDeleteAccounts() {
	int n = 20;
	HibernateModule.deleteAccounts();
	saveAccounts(n);
	assertEquals(n, HibernateModule.deleteAccounts());
    }
    
    public void testDeleteRecords() {
	int n = 50;
	HibernateModule.deleteRecords();
	saveRecords(n);
	assertEquals(n, HibernateModule.deleteRecords());
    }
    
    private void save1Account() {
	AccountDTO account = new AccountDTO();
	HibernateModule.saveAccount(account);
    }

    private void saveAccounts(int amount) {
	AccountDTO[] accounts = new AccountDTO[amount];
	for (int i = 0; i < amount; i++) {
	    accounts[i] = new AccountDTO();
	}
	HibernateModule.saveAccounts(accounts);
    }
    
    private void save1Record() {
	RecordDTO record = new RecordDTO();
	HibernateModule.saveRecord(record);
    }
    
    private void saveRecords(int amount) {
	RecordDTO[] records = new RecordDTO[amount];
	for (int i = 0; i < amount; i++) {
	    records[i] = new RecordDTO();
	}
	HibernateModule.saveRecords(records);
    }
}
