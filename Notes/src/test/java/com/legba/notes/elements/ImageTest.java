package com.legba.notes.elements;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Assert;

import com.legba.notes.elements.Image;
/**
 * This file is a test File for Audio.java
 * A for loop is used to Test all extension
 * types. This is done for all types of he 
 * file paths.
 * @author vc622
 *
 */
public class ImageTest {
	/**
	 * All the types of extensions that should be
	 * accepted as a image file. Accepted formats
	 * referenced from javafx.scene.media
	 */
	String[] extensionType = new String[] {
			".jpeg",".JPEG",
			".jpg",".JPG",
			".gif",".GIF",
			".png",".PNG",
			".bmp",".BMP"
	};
	
	@Test
	/**
	 * Tests if path types in array are Valid
	 * @param path Filepath
	 */
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
	
	/**
	 * Checks validity with a relative path types
	 * @param path Filepath
	 */	
	@Test
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
	
	/**
	 * Checks validity with a http path types
	 * @param path Filepath
	 */	
	@Test
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
		
		Image img = new Image(path);
		Boolean isValid = img.isValidPath(path);
		
		Assert.assertFalse(path + " is not valid", isValid);
	}
}
