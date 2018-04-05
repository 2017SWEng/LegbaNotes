package com.legba.notes.models;

import static org.junit.Assert.*;

import org.junit.Test;

import com.legba.notes.elements.Presentation;
import com.legba.notes.elements.Slide;
import com.legba.notes.models.AppModel;

public class AppModelTest {

	//Not sure if there needs to be a format for the file passed in because there currently isn't one
	/**
	 * Tests that passing in a file name correctly changes the file
	 */
	@Test
	public void testFile() {
		AppModel am = AppModel.getInstance();
		am.setFile("test");
		assertEquals(am.getFile(), "test");
	}
	
	/**
	 * Tests that passing in a null presentation ensures the ViewMode is HOMEPAGE
	 * Tests that passing in a presentation correctly changes the AppModels presentation
	 */
	@Test
	public void testPresentation() {
		AppModel am = AppModel.getInstance();
		Presentation pres = new Presentation();
		pres.addSlide(new Slide());
		pres.addSlide(new Slide());
		pres.addSlide(new Slide());
		
		am.setPres(null);
		assertEquals(am.getVeiwMode(), ViewMode.Mode.HOMEPAGE);
		
		am.setPres(pres);
		assertEquals(am.getPres(), pres);
	}
	
	/**
	 * Tests that passing in a VeiwMode correctly changes the VeiwMode
	 * Tests that when VeiwMode is set to a null value it is changed to the default HOMEPAGE
	 * Tests that when VeiwMode is HOMEPAGE the value of presentation is null
	 * Tests that if there is no presentation then VeiwMode won't change to VEIWING
	 */
	@Test
	public void testviewMode() {
		AppModel am = AppModel.getInstance();
		Presentation pres = new Presentation();
		pres.addSlide(new Slide());
		
		am.setVeiwMode(ViewMode.Mode.VEIWING);
		assertEquals(am.getVeiwMode(), ViewMode.Mode.VEIWING);
		
		am.setVeiwMode(null);
		assertEquals(am.getVeiwMode(), ViewMode.Mode.HOMEPAGE);
		
		am.setPres(pres);
		am.setVeiwMode(ViewMode.Mode.HOMEPAGE);
		assertEquals(am.getPres(), null);
		
		am.setPres(null);
		am.setVeiwMode(ViewMode.Mode.VEIWING);
		assertEquals(am.getVeiwMode(), ViewMode.Mode.HOMEPAGE);
	}

}