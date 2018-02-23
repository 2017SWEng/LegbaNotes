package com.legba.notes.app;

import org.junit.Test;
import org.junit.Assert;

import com.legba.notes.elements.Image;

public class ImageTest {

	@Test
	public void testGetPath() {
		Image img = new Image("Some Path");
		String test = img.getPath();
	
		Assert.assertEquals("These Paths match?", "Some Path", test);
	}

	@Test
	public void testSetPath() {
		Image img = new Image("Some Path");
		
		img.setPath("Different Path");
		Assert.assertEquals("The Path has Changed", "Different Path", img.getPath());
	}

}
