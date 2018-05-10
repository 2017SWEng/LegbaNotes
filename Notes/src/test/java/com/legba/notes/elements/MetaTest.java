package com.legba.notes.elements;

import static org.junit.Assert.*;

import org.junit.Test;

public class MetaTest {

	@Test	//tests that Meta function can be called
	public void meta_test() {
		Meta metaTing = new Meta("Author","Harry");
						
		assertEquals("Harry",metaTing.getValue());
		assertEquals("Author",metaTing.getKey());
	}
	
	@Test	//tests that Meta function can be called
	public void meta_setTest() {
		Meta metaTing = new Meta("Author","Harry");
		
		//test that key and value can be reset.
		metaTing.setKey("testKey");
		metaTing.setValue("testValue");
			
		assertEquals("testValue",metaTing.getValue());
		assertEquals("testKey",metaTing.getKey());
	}	
}
