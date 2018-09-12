package com.google.musicstore.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.musicstore.client.MusicStoreService;
import com.google.musicstore.client.dto.AccountDTO;
import com.google.musicstore.client.dto.RecordDTO;
import com.google.musicstore.server.model.Account;
import com.google.musicstore.server.model.Record;
import com.google.musicstore.util.HibernateUtil;

public class MusicStoreServiceImpl extends RemoteServiceServlet implements MusicStoreService {
    private static final long serialVersionUID = -4825786932285819100L;

    @Override
    public List<AccountDTO> getAccounts() {
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

    @Override
    public List<RecordDTO> getRecords() {
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

    @Override
    public Long saveAccount(AccountDTO accountDTO) {
	Account account = new Account(accountDTO);
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	session.save(account);
	session.getTransaction().commit();
	return account.getId();
    }

    @Override
    public void saveAccounts(AccountDTO[] accountsDTO) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	for (int i = 0; i < accountsDTO.length; i++) {
	    Account account = new Account(accountsDTO[i]);
	    session.save(account);
	}
	session.getTransaction().commit();
    }

    @Override
    public Long saveRecord(RecordDTO recordDTO) {
	Record record = new Record(recordDTO);
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	session.save(record);
	session.getTransaction().commit();
	return record.getId();
    }

    @Override
    public void saveRecords(RecordDTO[] recordsDTO) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	for (int i = 0; i < recordsDTO.length; i++) {
	    Record record = new Record(recordsDTO[i]);
	    session.save(record);
	}
	session.getTransaction().commit();
    }

    @Override
    public int deleteAccounts() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	Query query = session.createQuery("delete Account");
	int queryResult = query.executeUpdate();
	session.getTransaction().commit();
	return queryResult;
    }

    @Override
    public int deleteRecords() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	Query query = session.createQuery("delete Record");
	int queryResult = query.executeUpdate();
	session.getTransaction().commit();
	return queryResult;
    }

    @Override
    public int deleteAccountRecords() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	SQLQuery query = session.createSQLQuery("delete from account_record");
	int queryResult = query.executeUpdate();
	session.getTransaction().commit();
	return queryResult;
    }

    @Override
    public void saveRecordToAccount(AccountDTO accountDTO, RecordDTO recordDTO) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	Account account = (Account) session.load(Account.class, accountDTO.getId());
	Record record = (Record) session.load(Record.class, recordDTO.getId());
	account.addRecord(record);
	session.save(account);
	session.getTransaction().commit();
    }

    private AccountDTO createAccountDTO(Account account) {
	Set<Record> records = account.getRecords();
	Set<RecordDTO> recordDTOs = new HashSet<RecordDTO>(records != null ? records.size() : 0);
	if (records != null) {
	    for (Record record : records) {
		recordDTOs.add(createRecordDTO(record));
	    }
	}
	return new AccountDTO(account.getId(), account.getName(), account.getPassword(), recordDTOs);
    }

    private RecordDTO createRecordDTO(Record record) {
	return new RecordDTO(record.getId(), record.getTitle(), record.getYear(), record.getPrice());
    }
}