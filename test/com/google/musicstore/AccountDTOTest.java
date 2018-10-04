package com.google.musicstore;

import java.util.HashSet;
import java.util.Set;

import com.google.musicstore.client.model.AccountDTO;
import com.google.musicstore.client.model.RecordDTO;

import junit.framework.TestCase;

public class AccountDTOTest extends TestCase {
    private AccountDTO account;
    private Set<RecordDTO> records;

    public void setUp() {
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
