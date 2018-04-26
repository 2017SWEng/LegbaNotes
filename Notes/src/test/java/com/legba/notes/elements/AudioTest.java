package com.legba.notes.elements;

import org.junit.Test;

import static org.junit.Assert.fail;

import org.junit.Assert;

public class AudioTest {
	// All the types of extensions that should be accepted
	String[] extensionType = new String[] {
			".wav",".WAV",
			".aif",".AIF",
			".aiff",".AIFF",
			".m3u8",".M3U8",
			".mp3",".MP3"
	};

	@Test
	//Tests if all these path types are Valid
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
	
	@Test (expected = IllegalArgumentException.class)
	//Testing an invalid path. Should not work if there is no extension.
	public void testPath_noExtension() {
		String path = "test";
		
		Audio aud = new Audio(path);
		boolean isValid = aud.isValidPath(path);
		
		Assert.assertFalse(path + " is not valid", isValid);
	}
}
