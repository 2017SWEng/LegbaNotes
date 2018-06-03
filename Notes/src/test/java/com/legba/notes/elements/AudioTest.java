package com.legba.notes.elements;

import org.junit.Test;

import static org.junit.Assert.fail;

import org.junit.Assert;
/**
 * This file is a test File for Audio.java
 * A for loop is used to Test all extension
 * types. This is done for all types of he 
 * file paths.
 * @author vc622
 *
 */
public class AudioTest {
	/**
	 * All the types of extensions that should be
	 * accepted as an audio file. Accepted formats 
	 * referenced from javafx.scene.media
	 */
	String[] extensionType = new String[] {
			".wav",".WAV",
			".aif",".AIF",
			".aiff",".AIFF",
			".m3u8",".M3U8",
			".mp3",".MP3"
	};

	/**
	 * Tests if path types in array are Valid
	 * @param path Filepath
	 */
	@Test
	public void testPath() {
		Audio aud = null;
		for(int i = 0; i<extensionType.length; i++){
			String path = "test" + extensionType[i];
			
			try {
				 aud = new Audio(path);
			} catch (Exception IllegalArgument){
				fail();
			}
			boolean isValid = aud.isValidPath(path);
		
			Assert.assertTrue(path + " is not valid", isValid);
		};	
	}
	
	/**
	 * Checks validity with a relative path types
	 * @param path Filepath
	 */	
	@Test
	//Checks validity with a relative path types
	public void testPath_relative() {
		Audio aud = null;
		for(int i = 0; i<extensionType.length; i++){
			String path = "./test" + extensionType[i];
			try {
				 aud = new Audio(path);
			} catch (Exception IllegalArgument){
				fail();
			}
			boolean isValid = aud.isValidPath(path);
		
			Assert.assertTrue(path + " is not valid", isValid);
		};	
	}
	
	/**
	 * Checks validity with a http path types
	 * @param path Filepath
	 */	
	@Test
	//Checks validity with a http path type
	public void testPath_http() {
		Audio aud = null;
		for(int i = 0; i<extensionType.length; i++){
			String path = "http://example.com/test" + extensionType[i];
			try {
				 aud = new Audio(path);
			} catch (Exception IllegalArgument){
				fail();
			}
			boolean isValid = aud.isValidPath(path);
		
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
	//Testing an invalid path. Should not work if there is no extension.
	public void testPath_noExtension() {
		String path = "test";
		
		Audio aud = new Audio(path);
		boolean isValid = aud.isValidPath(path);
		
		Assert.assertFalse(path + " is not valid", isValid);
	}
}
