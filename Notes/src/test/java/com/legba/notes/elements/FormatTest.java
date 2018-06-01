package com.legba.notes.elements;

import static org.junit.Assert.*;

import org.junit.Assert;

/**
 * This file is a test File for Format.java
 * A sample string is used to test all 
 * Format.java functionality.
 * @author vc622
 */

public class FormatTest{
	
	String TestSample = "Example";
	
	/**
	 * Check to see if Text can be set.
	 */
	public void TestText(){
		Format Test = new Format();
		Test.setText(TestSample);
		Assert.assertEquals("Example", Test.getText());
	}
	/**
	 * Checking Font
	 * Testing with Arial Font
	 * and Calibri
	 **/
	public void FontTest(){
		Format Test = new Format();
		Test.setText(TestSample);
		Test.setFont("Arial");
		Assert.assertEquals("Arial", Test.getFont());
		Test.setFont("Calibri");
		Assert.assertEquals("Calibri", Test.getFont());
		Test.setFont(null);
		Assert.assertEquals("Calibri", Test.getFont());
	}
	/**
	 * Checking Size of Text can be set.
	 * Check with a small value (4) and 
	 * then a large value (24).
	 **/
	public void SizeTest(){
		Format Test = new Format();
		Test.setText(TestSample);
		Test.setTextsize(4);
		assertEquals((Integer)4,Test.getTextsize());
		Test.setTextsize(24);
		assertEquals((Integer)24,Test.getTextsize());
		Test.setTextsize(null);
		assertEquals((Integer)24,Test.getTextsize());
	}
	/**
	 * Checks Italic functionality
	 * If null should not change state.
	 **/
	public void ItalicTest(){
		Format Test = new Format();
		Test.setText(TestSample);
		Test.setItalic(true);
		Assert.assertTrue(Test.getItalic());
		Test.setItalic(null);
		Assert.assertTrue(Test.getItalic());
		Test.setItalic(false);
		Assert.assertTrue(Test.getItalic());
		Test.setItalic(null);
		Assert.assertTrue(Test.getItalic());
	}
	/**
	 * Checks Bold Functionality.
	 * If null should not change state.
	 **/
	public void BoldTest(){
		Format Test = new Format();
		Test.setText(TestSample);
		Test.setBold(true);
		Assert.assertTrue(Test.getBold());
		Test.setBold(null);
		Assert.assertTrue(Test.getBold());
		Test.setBold(false);
		Assert.assertTrue(Test.getBold());
		Test.setBold(null);
		Assert.assertTrue(Test.getBold());
	}
	/**
	 * Checks underline Functionality.
	 * If null should not change state.
	 **/
	public void underlineTest(){
		Format Test = new Format();
		Test.setText(TestSample);
		Test.setUnderline(true);
		Assert.assertTrue(Test.getUnderline());
		Test.setUnderline(null);
		Assert.assertTrue(Test.getUnderline());
		Test.setUnderline(false);
		Assert.assertTrue(Test.getUnderline());
		Test.setUnderline(null);
		Assert.assertTrue(Test.getUnderline());
	}
	
	/**
	 * Checks color Functionality. Testing 
	 * color with Blue and Black.
	 * If null should not change state.
	 **/	
	public void ColorTest(){
		Format Test = new Format();
		Test.setText(TestSample);
		Test.setColor(javafx.scene.paint.Color.AZURE);
		Assert.assertEquals(javafx.scene.paint.Color.AZURE, Test.getColor());
		Test.setColor(javafx.scene.paint.Color.BLACK);
		Assert.assertEquals(javafx.scene.paint.Color.BLACK, Test.getColor());
		Test.setColor(null);
		Assert.assertEquals(javafx.scene.paint.Color.BLACK, Test.getColor());
	}
	/**
	 * Checks fill Functionality.Testing 
	 * color with Blue and Black.
	 * If null should not change state.
	 **/	
	public void FillTest(){
		
		Format Test = new Format();
		Test.setText(TestSample);
		Test.setFill(javafx.scene.paint.Color.BLUE);
		Assert.assertEquals(javafx.scene.paint.Color.BLUE, Test.getFill());
		Test.setColor(javafx.scene.paint.Color.BLACK);
		Assert.assertEquals(javafx.scene.paint.Color.BLACK, Test.getFill());
		Test.setColor(null);
		Assert.assertEquals(javafx.scene.paint.Color.BLACK, Test.getFill());
		
	}
	
}