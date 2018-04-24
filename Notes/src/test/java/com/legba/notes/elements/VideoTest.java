package com.legba.notes.elements;

import org.junit.Test;
import org.junit.Assert;

public class VideoTest {
	// All the types of extensions that should be accepted
	String[] extensionType = new String[] {
			".mp4",".MP4",
			".m4a",".M4A",
			".m4v",".M4V",
			".m3u8",".M3U8",
			".fxm",".FXM",
			".flv",".FLV"
	};

	@Test
	//Tests if all these path types are Valid
	public void testPath() {
		for(int i = 0; i<extensionType.length; i++){
			String path = "test" + extensionType[i];
			Video vid = new Video(path);
			boolean isValid = vid.isValidPath(path);
		
			Assert.assertTrue(path + " is not valid", isValid);
		};	
	}
	
	@Test
	//Checks validity with a relative path types
	public void testPath_relative() {
		for(int i = 0; i<extensionType.length; i++){
			String path = "./test" + extensionType[i];
			Video vid = new Video(path);
			boolean isValid = vid.isValidPath(path);
		
			Assert.assertTrue(path + " is not valid", isValid);
		};	
	}
	
	@Test
	//Checks validity with a http path type
	public void testPath_http() {
		for(int i = 0; i<extensionType.length; i++){
			String path = "http://example.com/test" + extensionType[i];
			Video vid = new Video(path);
			boolean isValid = vid.isValidPath(path);
		
			Assert.assertTrue(path + " is not valid", isValid);
		};	

	}
	
	@Test
	//Testing an invalid path. Should not work if there is no extension.
	public void testPath_noExtension() {
		String path = "test";
		
		Video vid = new Video(path);
		boolean isValid = vid.isValidPath(path);
		
		Assert.assertFalse(path + " is not valid", isValid);
	}
}
