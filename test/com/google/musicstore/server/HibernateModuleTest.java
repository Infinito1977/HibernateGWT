package com.google.musicstore.server;

import java.util.List;

import com.google.musicstore.client.model.AccountDTO;
import com.google.musicstore.server.hibernate.HibernateModule;

import junit.framework.TestCase;

public class HibernateModuleTest extends TestCase {
    public void setUp() {
	HibernateModule.initializeHSQLDB();
    }
    
    public void testGet1Account() {
	save1Account(1l);
	List<AccountDTO> accounts = HibernateModule.getAccounts();
	assertEquals(1, accounts.size());
	HibernateModule.deleteAccounts();
    }

    public void testGet7Accounts() {
	save7Accounts();
	List<AccountDTO> accounts = HibernateModule.getAccounts();
	assertEquals(7, accounts.size());
	HibernateModule.deleteAccounts();
    }

    public void testGet1Record() {
	fail("not yet implemented");
    }

    public void testGet5Records() {
	fail("not yet implemented");
    }
    
    public void testSave1Account() {
	HibernateModule.deleteAccounts();
	assertEquals(1, save1Account(1l));
    }

    private long save1Account(long id) {
	AccountDTO account = new AccountDTO(id);
	return HibernateModule.saveAccount(account);
    }

    private void save7Accounts() {
	AccountDTO[] accounts = new AccountDTO[7];
	for (int i = 0; i < 7; i++) {
	    accounts[i] = new AccountDTO((long) i + 1);
	}
	HibernateModule.saveAccounts(accounts);
    }
}
