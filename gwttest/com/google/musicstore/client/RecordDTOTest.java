package com.google.musicstore.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.musicstore.client.model.RecordDTO;

public class RecordDTOTest extends GWTTestCase {
    private RecordDTO record;
    
    @Override
    public String getModuleName() {
	return "com.google.musicstore.MusicStore";
    }
    
    public void gwtSetUp() {
	record = new RecordDTO();
    }
    
    public void testID() {
	record.setId(5l);
	assertEquals(5, record.getId().longValue());
    }
    
    public void testTitle() {
	record.setTitle("Test-Titel");
	assertEquals("Test-Titel", record.getTitle());
    }
    
    public void testYear() {
	record.setYear(1999);
	assertEquals(1999, record.getYear());
    }
    
    public void testPrice() {
	record.setPrice(1);
	assertEquals(1.0, record.getPrice());
    }
}
