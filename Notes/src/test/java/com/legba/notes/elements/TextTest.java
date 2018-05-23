package com.legba.notes.elements;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;

/**
 * This file is a test File for Text.java
 * A standard ArrayList is made of which 
 * the contents are test for all Text.java 
 * functionality.
 * @author vc622
 */

public class TextTest {
	
	ArrayList<Object> TestList = new ArrayList<Object>();
	/**
	 * Testing whether Text can read content.
	 * If set to null the Contents should not change.		
	 */
	public void ContentTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text ContentText = new Text();
		ContentText.setContents(TestList);
		
		ArrayList<Object> ContentList = (ArrayList<Object>) ContentText.getContents();
		
		assertEquals("Hello", ContentList.get(0));
		assertEquals("Norman", ContentList.get(1));
		assertEquals("Sam", ContentList.get(2));
		assertEquals("David", ContentList.get(3));
		
		ContentText.setContents(null);
		
		assertEquals("Hello", ContentList.get(0));
		assertEquals("Norman", ContentList.get(1));
		assertEquals("Sam", ContentList.get(2));
		assertEquals("David", ContentList.get(3));
	}
	/**
	 * Testing whether text can be set to Bold.	
	 * If set to null bold should not change.			
	 */
	public void BoldTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text BoldText = new Text();
		BoldText.setContents(TestList);
		BoldText.setBold(true);
		Assert.assertTrue(BoldText.getBold());
		BoldText.setBold(null);
		Assert.assertTrue(BoldText.getBold());
		BoldText.setBold(false);
		Assert.assertTrue(BoldText.getBold());
		BoldText.setBold(null);
		Assert.assertTrue(BoldText.getBold());
	}
	/**
	 * Testing whether text can be set to Italic.
	 * If set to null Italic should not change.			
	 */
	public void ItalicTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text ItalicText = new Text();
		ItalicText.setContents(TestList);
		ItalicText.setItalic(true);
		Assert.assertTrue(ItalicText.getItalic());
		ItalicText.setItalic(null);
		Assert.assertTrue(ItalicText.getItalic());
		ItalicText.setItalic(false);
		Assert.assertTrue(ItalicText.getItalic());
		ItalicText.setItalic(null);
		Assert.assertTrue(ItalicText.getItalic());
	}
	/**
	 * Testing whether text can be underlined.
	 * If set to null Underline should not change.		
	 */
	public void UnderlineTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text UnderlineText = new Text();
		UnderlineText.setContents(TestList);
		UnderlineText.setUnderline(true);
		Assert.assertTrue(UnderlineText.getUnderline());
		UnderlineText.setUnderline(null);
		Assert.assertTrue(UnderlineText.getUnderline());
		UnderlineText.setUnderline(false);
		Assert.assertTrue(UnderlineText.getUnderline());
		UnderlineText.setUnderline(null);
		Assert.assertTrue(UnderlineText.getUnderline());
	}
	/**
	 * Testing whether setting Font works.
	 * If set to null the Font should not change.
	 */
	public void FontTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text FontText = new Text();
		FontText.setContents(TestList);
		FontText.setFont("Arial");
		assertEquals("Arial", FontText.getFont());
		FontText.setFont(null);
		assertEquals("Arial", FontText.getFont());
	}
	/**
	 * Testing whether setting Font works.
	 * If set to null the size of Text should not change.
	 */
	public void SizeTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text FontText = new Text();
		FontText.setContents(TestList);
		FontText.setTextsize(8);
		assertEquals((Integer)8,FontText.getTextsize());
		FontText.setTextsize(24);
		assertEquals((Integer)24,FontText.getTextsize());
		FontText.setTextsize(null);
		assertEquals((Integer)24,FontText.getTextsize());
	}
	/**
	 * Testing whether setting color works.
	 * If set to null the Color should not change. 
	 */
	public void ColorTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text ColorfulText = new Text();
		ColorfulText.setContents(TestList);
		ColorfulText.setColor(javafx.scene.paint.Color.AZURE);
		Assert.assertEquals(javafx.scene.paint.Color.AZURE, ColorfulText.getColor());
		ColorfulText.setColor(javafx.scene.paint.Color.RED);
		Assert.assertEquals(javafx.scene.paint.Color.RED, ColorfulText.getColor());
		ColorfulText.setColor(null);
		Assert.assertEquals(javafx.scene.paint.Color.RED, ColorfulText.getColor());
		
	}
	/**
	 * Testing whether setting color fill works.
	 * If set to null fill color should not change.
	 */	
	public void FillTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text FillText = new Text();
		FillText.setContents(TestList);
		FillText.setFill(javafx.scene.paint.Color.BLUE);
		Assert.assertEquals(javafx.scene.paint.Color.BLUE, FillText.getFill());
		FillText.setFill(javafx.scene.paint.Color.YELLOW);
		Assert.assertEquals(javafx.scene.paint.Color.YELLOW, FillText.getFill());
		FillText.setFill(null);
		Assert.assertEquals(javafx.scene.paint.Color.YELLOW, FillText.getFill());
		
	}
	/**
	 * Testing start time.
	 * If set to start time to null 
	 * start time should not change.
	 */	
	public void StartTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text ContentText = new Text();
		ContentText.setContents(TestList);
		ContentText.setStart(8);
		assertEquals((Integer)8,ContentText.getStart());
		ContentText.setStart(null);
		assertEquals((Integer)8,ContentText.getStart());
	}
	/**
	 * Testing Duration.
	 * If you set duration to null
	 * the duration should not change.
	 */	
	public void DurationTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text ContentText = new Text();
		ContentText.setContents(TestList);
		ContentText.setDuration(8);
		assertEquals((Integer)8,ContentText.getDuration());
		ContentText.setDuration(null);
		assertEquals((Integer)8,ContentText.getStart());
	}


}