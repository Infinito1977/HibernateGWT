package com.google.musicstore.server;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.musicstore.client.MusicStoreService;
import com.google.musicstore.domain.Account;
import com.google.musicstore.domain.Record;
import com.google.musicstore.util.HibernateUtil;

public class MusicStoreServiceImpl extends RemoteServiceServlet implements MusicStoreService {
    private static final long serialVersionUID = -4825786932285819100L;

    @Override
    public List<Account> getAccounts() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	List<Account> accounts = new ArrayList<Account>(session.createQuery("from Account").list());
	session.getTransaction().commit();
	return accounts;
    }

    @Override
    public List<Record> getRecords() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	List<Record> records = new ArrayList<Record>(session.createQuery("from Record").list());
	session.getTransaction().commit();
	return records;
    }

    @Override
    public Long saveAccount(Account account) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	session.save(account);
	session.getTransaction().commit();
	return account.getId();
    }

    @Override
    public Long saveRecord(Record record) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	session.save(record);
	session.getTransaction().commit();
	return record.getId();
    }

    @Override
    public void saveRecordToAccount(Account account, Record record) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	account = (Account) session.load(Account.class, account.getId());
	record = (Record) session.load(Record.class, record.getId());
	account.addRecord(record);
	session.save(account);
	session.getTransaction().commit();
    }
}