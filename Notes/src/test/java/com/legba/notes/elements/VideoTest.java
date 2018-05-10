package com.legba.notes.elements;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Assert;
/**
 * This file is a test File for Video.java
 * A for loop is used to Test all extension
 * types. This is done for all types of he 
 * file paths.
 * @author vc622
 *
 */
public class VideoTest {
	/**
	 * All the types of extensions that should be
	 * accepted as a video file. Accepted formats 
	 * referenced from javafx.scene.media
	 */
	String[] extensionType = new String[] {
			".mp4",".MP4",
			".m4a",".M4A",
			".m4v",".M4V",
			".m3u8",".M3U8",
			".fxm",".FXM",
			".flv",".FLV"
	};
	/**
	 * Tests if path types in array are Valid
	 * @param path Filepath
	 */
	@Test
	public void testPath() {
		Video vid = null;
		for(int i = 0; i<extensionType.length; i++){
			String path = "test" + extensionType[i];
			try{
				vid = new Video(path);
			}catch(Exception IllegalArgument){
				fail();
			}
			boolean isValid = vid.isValidPath(path);
		
			Assert.assertTrue(path + " is not valid", isValid);
		};	
	}
	
	@Test
	/**
	 * Checks validity with a relative path types
	 * @param path Filepath
	 */	
	public void testPath_relative() {
		Video vid = null;
		for(int i = 0; i<extensionType.length; i++){
			String path = "./test" + extensionType[i];
			try{
				vid = new Video(path);
			}catch(Exception IllegalArgument){
				fail();
			}
			boolean isValid = vid.isValidPath(path);
		
			Assert.assertTrue(path + " is not valid", isValid);
		};	
	}
	
	@Test
	/**
	 * Checks validity with a http path types
	 * @param path Filepath
	 */	
	public void testPath_http() {
		Video vid = null;
		for(int i = 0; i<extensionType.length; i++){
			String path = "http://example.com/test" + extensionType[i];
			try{
				vid = new Video(path);
			}catch(Exception IllegalArgument){
				fail();
			}
			boolean isValid = vid.isValidPath(path);
		
			Assert.assertTrue(path + " is not valid", isValid);
		};	
	}
	/**
	 * Intentionally testing an invalid path.  
	 * The test should fail if the path is not 
	 * a valid one.
	 * @param path Filepath
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testPath_noExtension() {
		String path = "test";
		
		Video vid = new Video(path);
		boolean isValid = vid.isValidPath(path);
		
		Assert.assertFalse(path + " is not valid", isValid);
	}
	
}
