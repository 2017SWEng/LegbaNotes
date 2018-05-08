package com.legba.notes.elements;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;


import org.junit.Assert;

public class TextTest {
	
	ArrayList<Object> TestList = new ArrayList<Object>();
	
	
	
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
	}
	
	public void BoldTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text BoldText = new Text();
		BoldText.setContents(TestList);
		BoldText.setBold(true);
		Assert.assertTrue(BoldText.getBold());
	}
	
	public void FontTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text FontText = new Text();
		FontText.setContents(TestList);
		FontText.setFont("Arial");
		assertEquals("Arial", FontText.getFont());
	}
	
	public void ItalicTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text ItalicText = new Text();
		ItalicText.setContents(TestList);
		ItalicText.setItalic(true);
		Assert.assertTrue(ItalicText.getItalic());
	}
	
	public void SizeTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text FontText = new Text();
		FontText.setContents(TestList);
		FontText.setTextsize(8);
		assertEquals(8L,(double)FontText.getTextsize());
	}
	
	public void UnderlineTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text UnderlineText = new Text();
		UnderlineText.setContents(TestList);
		UnderlineText.setUnderline(true);
		Assert.assertTrue(UnderlineText.getUnderline());
	}
	
	public void ColorTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text ColorfulText = new Text();
		ColorfulText.setContents(TestList);
		ColorfulText.setColor(javafx.scene.paint.Color.AZURE);
		Assert.assertEquals(javafx.scene.paint.Color.AZURE, ColorfulText.getColor());
		
	}
	
	public void FillTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text FillText = new Text();
		FillText.setContents(TestList);
		FillText.setColor(javafx.scene.paint.Color.BLUE);
		Assert.assertEquals(javafx.scene.paint.Color.BLUE, FillText.getFill());
		
	}
	
	public void StartTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text ContentText = new Text();
		ContentText.setContents(TestList);
		ContentText.setStart(8);
		assertEquals(8L,(double)ContentText.getStart());
	}
	
	public void DurationTest(){
		TestList.add("Hello");
		TestList.add("Norman");
		TestList.add("SAM");
		TestList.add("David");
		
		Text ContentText = new Text();
		ContentText.setContents(TestList);
		ContentText.setDuration(8);
		assertEquals(8L,(double)ContentText.getDuration());
	}


}