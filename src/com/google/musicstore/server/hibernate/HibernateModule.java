package com.google.musicstore.server.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.google.musicstore.client.model.AccountDTO;
import com.google.musicstore.client.model.RecordDTO;
import com.google.musicstore.server.model.Account;
import com.google.musicstore.server.model.Record;
import com.google.musicstore.util.HibernateUtil;

public class HibernateModule {
    public static List<AccountDTO> getAccounts() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	List<Account> accounts = new ArrayList<Account>(session.createQuery("from Account").list());
	List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>(accounts != null ? accounts.size() : 0);
	if (accounts != null) {
	    for (Account account : accounts) {
		accountDTOs.add(createAccountDTO(account));
	    }
	}
	session.getTransaction().commit();
	return accountDTOs;
    }
    
    public static List<RecordDTO> getRecords() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	List<Record> records = new ArrayList<Record>(session.createQuery("from Record").list());
	List<RecordDTO> recordDTOs = new ArrayList<RecordDTO>(records != null ? records.size() : 0);
	if (records != null) {
	    for (Record record : records) {
		recordDTOs.add(createRecordDTO(record));
	    }
	}
	session.getTransaction().commit();
	return recordDTOs;
    }
    
    public static Long saveAccount(AccountDTO accountDTO) {
	Account account = new Account(accountDTO);
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	session.save(account);
	session.getTransaction().commit();
	return account.getId();
    }
    
    public static void saveAccounts(AccountDTO[] accountsDTO) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	for (int i = 0; i < accountsDTO.length; i++) {
	    Account account = new Account(accountsDTO[i]);
	    session.save(account);
	}
	session.getTransaction().commit();
    }
    
    public static Long saveRecord(RecordDTO recordDTO) {
	Record record = new Record(recordDTO);
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	session.save(record);
	session.getTransaction().commit();
	return record.getId();
    }
    
    public static void saveRecords(RecordDTO[] recordsDTO) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	for (int i = 0; i < recordsDTO.length; i++) {
	    Record record = new Record(recordsDTO[i]);
	    session.save(record);
	}
	session.getTransaction().commit();
    }
    
    public static int deleteAccounts() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	Query query = session.createQuery("delete Account");
	int queryResult = query.executeUpdate();
	session.getTransaction().commit();
	return queryResult;
    }
    
    public static int deleteRecords() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	Query query = session.createQuery("delete Record");
	int queryResult = query.executeUpdate();
	session.getTransaction().commit();
	return queryResult;
    }
    
    public static int deleteAccountRecords() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	SQLQuery query = session.createSQLQuery("delete from account_record");
	int queryResult = query.executeUpdate();
	session.getTransaction().commit();
	return queryResult;
    }
    
    public static void saveRecordToAccount(AccountDTO accountDTO, RecordDTO recordDTO) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	Account account = (Account) session.load(Account.class, accountDTO.getId());
	Record record = (Record) session.load(Record.class, recordDTO.getId());
	account.addRecord(record);
	session.save(account);
	session.getTransaction().commit();
    }

    public static AccountDTO createAccountDTO(Account account) {
	Set<Record> records = account.getRecords();
	Set<RecordDTO> recordDTOs = new HashSet<RecordDTO>(records != null ? records.size() : 0);
	if (records != null) {
	    for (Record record : records) {
		recordDTOs.add(createRecordDTO(record));
	    }
	}
	return new AccountDTO(account.getId(), account.getName(), account.getPassword(), recordDTOs);
    }
    
    private static RecordDTO createRecordDTO(Record record) {
	return new RecordDTO(record.getId(), record.getTitle(), record.getYear(), record.getPrice());
    }
}
