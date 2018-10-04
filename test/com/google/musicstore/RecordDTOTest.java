package com.google.musicstore;

import com.google.musicstore.client.model.RecordDTO;

import junit.framework.TestCase;

public class RecordDTOTest extends TestCase {
    private RecordDTO record;
    
    public void setUp() {
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
