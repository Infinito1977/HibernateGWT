package com.google.musicstore.client;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ModelTestSuite extends GWTTestSuite {
    public static Test suite() {
	TestSuite suite = new TestSuite("Test client model (DTO classes)");
	suite.addTestSuite(AccountDTOTest.class);
	suite.addTestSuite(RecordDTOTest.class);
	return suite;
    }
}
