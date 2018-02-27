package com.legba.notes.elements;

import org.junit.Test;
import org.junit.Assert;

public class VideoTest {

	@Test
	public void testPath_mp4() {
		String path = "test.mp4";
		
		Video vid = new Video(path);
		String res = vid.getPath();
	
		Assert.assertEquals("These Paths match?", path, res);
	}

	@Test
	public void testPath_MP4() {
		String path = "test.MP4";
		
		Video vid = new Video(path);
		String res = vid.getPath();
	
		Assert.assertEquals("These Paths match?", path, res);
	}
	
	@Test
	public void testPath_relitive() {
		String path = "./test.mp4";
		
		Video vid = new Video(path);
		String res = vid.getPath();
	
		Assert.assertEquals("These Paths match?", path, res);
	}
	
	@Test
	public void testPath_http() {
		String path = "http://example.com/test.mp4";
		
		Video vid = new Video(path);
		String res = vid.getPath();
	
		Assert.assertEquals("These Paths match?", path, res);
	}
	
	@Test
	public void testPath_noExtension() {
		String path = "test";
		
		Video vid = new Video(path);
		String res = vid.getPath();
	
		Assert.assertNotEquals("These Paths don't match?", path, res);
	}
}
