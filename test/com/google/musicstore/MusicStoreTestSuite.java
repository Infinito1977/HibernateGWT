package com.google.musicstore;

import com.google.musicstore.server.HibernateModuleTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class MusicStoreTestSuite extends TestSuite {
    public static Test suite() {
	TestSuite suite= new TestSuite("JRE Tests");
	suite.addTestSuite(AccountDTOTest.class);
	suite.addTestSuite(RecordDTOTest.class);
	suite.addTestSuite(HibernateModuleTest.class);
	return suite;
    }
}
