package com.google.musicstore.client;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.musicstore.client.model.AccountDTO;
import com.google.musicstore.client.model.RecordDTO;

public class AccountDTOTest extends GWTTestCase {
    private AccountDTO account;
    private Set<RecordDTO> records;
    
    @Override
    public String getModuleName() {
	return "com.google.musicstore.MusicStore";
    }
    
    public void gwtSetUp() {
	account = new AccountDTO();
	records = new HashSet<RecordDTO>();
	for (int i = 1; i <= 5; i++) {
	    records.add(new RecordDTO((long) i, "Titel " + i, 1999, 1));
	}
    }
    
    public void testID() {
	account.setId(5l);
	assertEquals(5, account.getId().longValue());
    }

    public void testName() {
	account.setName("Testname");
	assertEquals("Testname", account.getName());
    }

    public void testPassword() {
	account.setPassword("pwd");
	assertEquals("pwd", account.getPassword());
    }

    public void testRecordCount() {
	assertEquals(5, records.size());
    }
}
