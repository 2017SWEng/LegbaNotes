package com.legba.notes.controllers;

import java.util.List;

import javax.xml.bind.JAXBException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.legba.notes.elements.*;

public class FileSystemControllerTest {

	
	
Presentation pres1;
	
	@Before
    public void setUp_pres1() {
        this.pres1 = new Presentation();
        
        Meta meta1 = new Meta("author","John Doe");
        Meta meta2 = new Meta("version","v0.0.1");
        
        this.pres1.addMeta(meta1);
        this.pres1.addMeta(meta2);
        
        Slide s1 = new Slide();
        s1.setDuration(10);
        s1.setStart(5);
        
        Text t1 = new Text();
        t1.setTextsize(12);
        s1.addText(t1);

        Video v1 = new Video("./test.mp4");
        s1.addVideo(v1);
        
        Slide s2 = new Slide();
        s2.setDuration(11);
        s2.setStart(1);
        
        this.pres1.addSlide(s1);
        this.pres1.addSlide(s2);
    }
	
	@Test
	public void test_saveload() throws JAXBException {

		
		FileSystemController fsc = new FileSystemController();
		fsc.saveXmlFile("./target/test.xml", this.pres1);
		
		Presentation pres2 = fsc.loadXmlFile("./target/test.xml");
		
		Assert.assertEquals(pres1.getMeta(0).getKey(), pres2.getMeta(0).getKey());
		Assert.assertEquals(pres1.getMeta(0).getValue(), pres2.getMeta(0).getValue());
	
		//TODO: test the rest of the presentaion
	
	}
	
	@Test
	public void test_load() throws JAXBException {


		FileSystemController fsc = new FileSystemController();		
		Presentation presentation = fsc.loadXmlFile("./example.pws");		
		
		
		Meta meta1 = presentation.getMeta(0);
		
		Assert.assertEquals("Meta author", meta1.getKey(),"author");
		Assert.assertEquals("Meta author", meta1.getValue(),"Stuart Porter");
		
		List<Slide> slides = presentation.getSlide();
		Assert.assertEquals("Correct number of slides", slides.size(),6);
		
		//Assert.assertEquals("First ele is text",slides.get(0).getSlideElement(0).getClass(),Text.class);

	}

}
