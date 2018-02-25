package com.legba.notes.elements;

import org.junit.Test;
import org.junit.Assert;

import com.legba.notes.elements.Image;

public class ImageTest {

	@Test
	public void testPath_jpeg() {
		String path = "test.jpeg";
		
		Image img = new Image(path);
		String res = img.getPath();
	
		Assert.assertEquals("These Paths match?", path, res);
	}

	@Test
	public void testPath_jpg() {
		String path = "test.jpg";
		
		Image img = new Image(path);
		String res = img.getPath();
	
		Assert.assertEquals("These Paths match?", path, res);
	}
	
	@Test
	public void testPath_relitive() {
		String path = "./test.jpg";
		
		Image img = new Image(path);
		String res = img.getPath();
	
		Assert.assertEquals("These Paths match?", path, res);
	}
	
	@Test
	public void testPath_http() {
		String path = "http://example.com/test.jpg";
		
		Image img = new Image(path);
		String res = img.getPath();
	
		Assert.assertEquals("These Paths match?", path, res);
	}
	
	@Test
	public void testPath_noExtension() {
		String path = "test";
		
		Image img = new Image(path);
		String res = img.getPath();
	
		Assert.assertNotEquals("These Paths don't match?", path, res);
	}
}
