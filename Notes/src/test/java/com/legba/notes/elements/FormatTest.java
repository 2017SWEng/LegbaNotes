package com.legba.notes.elements;

import org.junit.Test;

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
	
	public void TestText(){
		Format Test = new Format();
		Test.setText(TestSample);
		Assert.assertEquals("Example", Test.getText());
	}

	public void FontTest(){
		Format Test = new Format();
		Test.setText(TestSample);
		Test.setFont("Arial");
		Assert.assertEquals("Arial", Test.getFont());
	}
	
	public void SizeTest(){
		Format Test = new Format();
		Test.setText(TestSample);
		Test.setTextsize(4);
		assertEquals((Integer)4,Test.getTextsize());
	}
	
	public void ItalicTest(){
		Format Test = new Format();
		Test.setText(TestSample);
		Test.setItalic(true);
		Assert.assertTrue(Test.getItalic());
	}
	
	public void BoldTest(){
		Format Test = new Format();
		Test.setText(TestSample);
		Test.setBold(true);
		Assert.assertTrue(Test.getBold());		
	}
	
	public void underlineTest(){
		Format Test = new Format();
		Test.setText(TestSample);
		Test.setUnderline(true);
		Assert.assertTrue(true);
	}
	
	public void ColorTest(){
		Format Test = new Format();
		Test.setText(TestSample);
		Test.setColor(javafx.scene.paint.Color.AZURE);
		Assert.assertEquals(javafx.scene.paint.Color.AZURE, Test.getColor());
	}
	
	public void FillTest(){
		
		Format Test = new Format();
		Test.setText(TestSample);
		Test.setFill(javafx.scene.paint.Color.BLUE);
		Assert.assertEquals(javafx.scene.paint.Color.BLUE, Test.getFill());
		
	}
	
}