package com.legba.notes.elements;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import javafx.scene.paint.Color;

public class SlideTest {

	@Test	//tests that Slide is created along with relevant arrays.
	public void presentaion_created() {
		Slide testSlide = new Slide();
		assertNotNull(testSlide);
		assertNotNull(testSlide.getAudios());
		assertNotNull(testSlide.getImages());
		assertNotNull(testSlide.getShapes());
		assertNotNull(testSlide.getTexts());
		assertNotNull(testSlide.getVideos());
		
		assertEquals(0,testSlide.getAudios().size());
		assertEquals(0,testSlide.getImages().size());
		assertEquals(0,testSlide.getShapes().size());
		assertEquals(0,testSlide.getTexts().size());
		assertEquals(0,testSlide.getVideos().size());
	}
	
	@Test	//tests audio element arrays can be set to audio list in the slide.
	public void audio_test() {
		Slide testSlide = new Slide();
		List<Audio> audioList = new ArrayList<Audio>();
		audioList.add(new Audio("M:/Year 3/SWENG/audio_samples/sample3.wav"));
		audioList.add(new Audio("M:/Year 3/SWENG/audio_samples/sample2.wav"));
		testSlide.setAudios(audioList);
		
		assertEquals(2,testSlide.getAudios().size());
		assertEquals(audioList.get(0),testSlide.getAudios().get(0));
		
		testSlide.addAudio(new Audio ("M:/Year 3/SWENG/audio_samples/sample1.wav"));
		
		//tests that further audio elements can be added to array in slide.
		assertEquals(3,testSlide.getAudios().size());
		assertEquals(audioList.get(2),testSlide.getAudios().get(2));
	}
	
	@Test	//tests image element arrays can be set to image list in the slide.
	public void image_test() {
		Slide testSlide = new Slide();
		List<Image> imageList = new ArrayList<Image>();
		imageList.add(new Image("M:/Year 3/SWENG/image_samples/sample3.jpg"));
		imageList.add(new Image("M:/Year 3/SWENG/image_samples/sample2.jpg"));
		testSlide.setImages(imageList);
		
		assertEquals(2,testSlide.getImages().size());
		assertEquals(imageList.get(1),testSlide.getImages().get(1));
		
		testSlide.addImage(new Image ("M:/Year 3/SWENG/image_samples/sample1.jpg"));
		
		//tests that further image elements can be added to array in slide.
		assertEquals(3,testSlide.getImages().size());
		assertEquals(imageList.get(2),testSlide.getImages().get(2));
	}
	
	@Test	//tests shape element arrays can be set to shape list in the slide.
	public void shape_test() {
		Slide testSlide = new Slide();
		List<Shape> shapeList = new ArrayList<Shape>();
		shapeList.add(new Shape("rectangle"));
		shapeList.add(new Shape("ellipse"));
		testSlide.setShapes(shapeList);
		
		assertEquals(2,testSlide.getShapes().size());
		assertEquals(shapeList.get(1),testSlide.getShapes().get(1));
		
		testSlide.addShape(new Shape("line"));
		
		//tests that further shape elements can be added to array in slide.
		assertEquals(3,testSlide.getShapes().size());
		assertEquals(shapeList.get(2),testSlide.getShapes().get(2));
	}
	
	@Test	//tests text element arrays can be set to text list in the slide.
	public void text_test() {
		Slide testSlide = new Slide();
		List<Text> textList = new ArrayList<Text>();
		textList.add(new Text("Here is some text"));
		textList.add(new Text("And some more text"));
		testSlide.setTexts(textList);
		
		assertEquals(2,testSlide.getTexts().size());
		assertEquals(textList.get(1),testSlide.getTexts().get(1));
		
		testSlide.addText(new Text("Texty texty more text."));
		
		//tests that further text elements can be added to array in slide.
		assertEquals(3,testSlide.getTexts().size());
		assertEquals(textList.get(2),testSlide.getTexts().get(2));
	}
	
	@Test	//tests video element arrays can be set to video list in the slide.
	public void video_test() {
		Slide testSlide = new Slide();
		List<Video> videoList = new ArrayList<Video>();
		videoList.add(new Video("M:/Year 3/SWENG/video_samples/sample3.mp4"));
		videoList.add(new Video("M:/Year 3/SWENG/video_samples/sample2.mp4"));
		testSlide.setVideos(videoList);
		
		assertEquals(2,testSlide.getVideos().size());
		assertEquals(videoList.get(1),testSlide.getVideos().get(1));
		
		testSlide.addVideo(new Video ("M:/Year 3/SWENG/video_samples/sample1.mp4"));
		
		//tests that further video elements can be added to array in slide.
		assertEquals(3,testSlide.getVideos().size());
		assertEquals(videoList.get(2),testSlide.getVideos().get(2));
	}
	
	@Test	//tests setting and getting of start integer.
	public void start_test() {
		Slide testSlide = new Slide();
		testSlide.setStart(15);
		
		assertEquals((Integer)15,testSlide.getStart());
	}
	
	@Test	//tests setting and getting of duration integer.
	public void duration_test() {
		Slide testSlide = new Slide();
		testSlide.setDuration(306);
		
		assertEquals((Integer)306,testSlide.getDuration());
	}
	
	@Test	//tests setting and getting of colour.
	public void color_test() {
		Slide testSlide = new Slide();
		testSlide.setColor(Color.BLUEVIOLET);
		
		assertEquals(Color.BLUEVIOLET,testSlide.getColor());
	}
	
	@Test	//tests setting and getting of fill colour.
	public void fill_test() {
		Slide testSlide = new Slide();
		testSlide.setFill(Color.RED);
		
		assertEquals(Color.RED,testSlide.getFill());
	}
	
	@Test	//tests setting and getting of font type.
	public void font_test() {
		Slide testSlide = new Slide();
		testSlide.setFont(null);
		
		//test that default font (Calibri) is set if font is null.
		assertEquals("Calibri",testSlide.getFont());
		
		testSlide.setFont("Arial");
		//Test that valid font can be set.
		assertEquals("Arial", testSlide.getFont());
	}

	@Test	//tests setting and getting of italic, bold, underline boolean.
	public void boolean_test() {
		Slide testSlide = new Slide();
		testSlide.setItalic(true);
		testSlide.setBold(true);
		testSlide.setUnderline(true);
		
		assertEquals(true,testSlide.getItalic());
		assertEquals(true,testSlide.getBold());
		assertEquals(true,testSlide.getUnderline());
	}
	
	@Test	//tests setting and getting of font size.
	public void fontSize_test() {
		Slide testSlide = new Slide();
		testSlide.setTextsize(12);
		
		assertEquals((Integer)12,testSlide.getTextsize());
	}
}
