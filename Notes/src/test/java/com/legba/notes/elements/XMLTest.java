package com.legba.notes.elements;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class XMLTest {

	
	
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
        t1.setSize(12);
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
	public void test_marshaller() throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Presentation.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(this.pres1, System.out);
	
	
	}
	
	@Test
	public void test_unmarshaller() throws JAXBException {

		File file = new File("./example.pws");
		JAXBContext jaxbContext = JAXBContext.newInstance(Presentation.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Presentation presentation = (Presentation) jaxbUnmarshaller.unmarshal(file);
		
		
		
		System.out.println(presentation);
		
		
		JAXBContext jaxbContext2 = JAXBContext.newInstance(Presentation.class);
		Marshaller jaxbMarshaller = jaxbContext2.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(presentation, System.out);

		
		
		
		
		Meta meta1 = presentation.getMeta(0);
		
		Assert.assertEquals("Meta author", meta1.getKey(),"author");
		Assert.assertEquals("Meta author", meta1.getValue(),"Stuart Porter");
		
		List<Slide> slides = presentation.getSlide();
		Assert.assertEquals("Correct number of slides", slides.size(),5);
		
		//Assert.assertEquals("First ele is text",slides.get(0).getSlideElement(0).getClass(),Text.class);

	}

}
