package com.legba.notes.app;

import static org.junit.Assert.*;

import org.junit.Test;

import com.legba.notes.elements.Image;

public class ImageTest {

	@Test
	public void testGetPath() {
		Image img = new Image("Some Path");
		String test = img.getPath();
	
		assertEquals("These Paths match?", "Some Path", test);
	}

	@Test
	public void testSetPath() {
		Image img = new Image("Some Path");
		
		img.setPath("Different Path");
		assertEquals("The Path has Changed", "Different Path", img.getPath());
	}

}
