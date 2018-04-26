package com.legba.notes.elements;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Assert;

import com.legba.notes.elements.Image;

public class ImageTest {
	// All the types of extensions that should be accepted
	String[] extensionType = new String[] {
			".jpeg",".JPEG",
			".jpg",".JPG",
			".gif",".GIF",
			".png",".PNG",
			".bmp",".BMP"
	};
	
	@Test
	//Tests if all these path types in array are Valid
	public void testPath() {	
		Image img = null;
		for(int i = 0; i<extensionType.length; i++){
			String path = "test" + extensionType[i];
			try{
				img = new Image(path);
			}catch ( Exception illegalArgument){
				fail();
			}
			Boolean isValid = img.isValidPath(path);
			Assert.assertTrue(path + " is not valid", isValid);
		};
	}
	
	@Test
	//Checks validity with a relative path types
	public void testPath_relative() {
		Image img = null;
		for(int i = 0; i<extensionType.length; i++){
			String path = "./test" + extensionType[i];
			try{
				img = new Image(path);
			}catch ( Exception illegalArgument){
				fail();
			}
			Boolean isValid = img.isValidPath(path);
		
			Assert.assertTrue(path + " is not valid", isValid);
		};
	}
	
	@Test
	//Checks validity with a http path type
	public void testPath_http() {
		Image img = null;
		for(int i = 0; i<extensionType.length; i++){
			String path = "http://example.com/test" + extensionType[i];
			try{
				img = new Image(path);
			}catch ( Exception illegalArgument){
				fail();
			}
			Boolean isValid = img.isValidPath(path);
		
			Assert.assertTrue(path + " is not valid", isValid);
		};

	}
	
	@Test (expected = IllegalArgumentException.class)
	//Testing an invalid path. Should not work if there is no extension.
	public void testPath_noExtension() {
		String path = "test";
		
		Image img = new Image(path);
		Boolean isValid = img.isValidPath(path);
		
		Assert.assertFalse(path + " is not valid", isValid);
	}
}
