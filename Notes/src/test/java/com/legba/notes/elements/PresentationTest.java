package com.legba.notes.elements;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import javafx.scene.paint.Color;
public class PresentationTest {


	@Test	//tests that Presentation and both Meta and Slide arrays are created.
	public void presentaion_created() {
		Presentation testPres = new Presentation();
		assertNotNull(testPres);
		assertNotNull(testPres.getMeta());
		assertEquals(0,testPres.getMeta().size());
		assertNotNull(testPres.getSlide());
		assertEquals(0,testPres.getSlide().size());		
	}
	
	@Test	//tests that data can set to Meta Array
	public void set_meta() {
		Presentation testPres2 = new Presentation();
		Meta metaTing = new Meta("Author","Harry");
		Meta metaTing2 = new Meta("Key 2","Value 2");
		List<Meta> metaList = new ArrayList<Meta>();
		metaList.add(metaTing);
		metaList.add(metaTing2);
		testPres2.setMeta(metaList);
		
		assertNotNull(testPres2.getMeta());
		assertEquals(2,testPres2.getMeta().size());
		assertEquals("Harry",testPres2.getMeta(0).getValue());
	}
	
	@Test	//tests that data can be added to Meta Array
	public void add_meta() {
		Presentation testPres3 = new Presentation();
		Meta metaTing = new Meta("Author","Harry");
		Meta metaTing2 = new Meta("Key 2","Value 2");
		Meta metaTing3 = new Meta("Added Data Key","Added Data Value");
		List<Meta> metaList = new ArrayList<Meta>();
		metaList.add(metaTing);
		metaList.add(metaTing2);
		testPres3.setMeta(metaList);
		testPres3.addMeta(metaTing3); //More data added to array.
		
		assertNotNull(testPres3.getMeta());
		assertEquals(3,testPres3.getMeta().size());
		assertEquals("Added Data Key",testPres3.getMeta(2).getKey());
		testPres3.getMeta().forEach((x)-> {System.out.println(x.getKey()+" : "+x.getValue());});		
	}
	
	@Test	//tests that data can set to Slide Array
	public void set_slide() {
		Presentation testPres4 = new Presentation();
		Slide slideTing = new Slide();
		Slide slideTing2 = new Slide();
		List<Slide> slideList = new ArrayList<Slide>();
		slideList.add(slideTing);
		slideList.add(slideTing2);
		testPres4.setSlide(slideList);
		
		assertNotNull(testPres4.getSlide());
		assertEquals(2,testPres4.getSlide().size());	
	}
	
	@Test	//tests that data can be added to Slide Array
	public void add_slide_data() {
		Presentation testPres5 = new Presentation();
		Slide slideTing = new Slide();
		Slide slideTing2 = new Slide();
		Slide slideTing3 = new Slide();
		List<Slide> slideList = new ArrayList<Slide>();
		slideList.add(slideTing);
		slideList.add(slideTing2);
		testPres5.setSlide(slideList);
		testPres5.addSlide(slideTing3); //third slide added to array.
		
		assertNotNull(testPres5.getSlide());
		assertEquals(3,testPres5.getSlide().size());
		assertSame(slideTing3, testPres5.getSlide(2));
	}
	
	@Test	//Tests Color is correctly set.
	public void ColorTest() {
		Presentation pres_parameters = new Presentation();
		pres_parameters.setColor(Color.DODGERBLUE);
		
		assertEquals(Color.DODGERBLUE, (Color)pres_parameters.getColor());
	}
	
	@Test	//Tests Fill Color is correctly set.
	public void FillTest() {
		Presentation pres_parameters = new Presentation();
		pres_parameters.setFill(Color.ALICEBLUE);		
		
		assertEquals(Color.ALICEBLUE, (Color)pres_parameters.getFill());
	}		
	
	@Test	//Tests font is correctly set.
	public void FontTest() {
		Presentation pres_parameters = new Presentation();
		pres_parameters.setFont("Calibri");

		assertEquals("Calibri", pres_parameters.getFont());		
	}		
	
	@Test	//Tests Italic boolean is correctly set.
	public void italicTest() {
		Presentation pres_parameters = new Presentation();
		pres_parameters.setItalic(true);

		assertEquals(true, pres_parameters.getItalic());
	}		
	
	@Test	//Tests Bold boolean is correctly set.
	public void boldTest() {
		Presentation pres_parameters = new Presentation();
		pres_parameters.setBold(true);
	
		assertEquals(true, pres_parameters.getBold());
	}		
	
	@Test	//Tests Underline boolean is correctly set.
	public void underlineTest() {
		Presentation pres_parameters = new Presentation();
		pres_parameters.setUnderline(true);

		assertEquals(true, pres_parameters.getUnderline());
	}		
	
	@Test	//Tests text size is correctly set.
	public void textSizeTest() {
		Presentation pres_parameters = new Presentation();
		pres_parameters.setTextsize(12);
		
		assertEquals((Integer)12, pres_parameters.getTextsize());		
	}		
	
}