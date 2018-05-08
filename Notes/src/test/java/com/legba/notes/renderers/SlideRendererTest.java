package com.legba.notes.renderers;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import org.junit.Test;

import com.legba.notes.elements.Audio;
import com.legba.notes.elements.Image;
import com.legba.notes.elements.Shape;
import com.legba.notes.elements.Slide;
import com.legba.notes.elements.Text;
import com.legba.notes.elements.Video;

import org.junit.BeforeClass;

public class SlideRendererTest {

	static Slide audioSlide;
	static Slide shapeSlide;
	static Slide textSlide;
	static Slide multiSlide;
	static Slide videoSlide;
	static Slide imageSlide;

	
	static SlideRenderer sr;
	
	static int audioRenderCalled = 0;
	static int vectorRenderCalled = 0;
	static int textRenderCalled = 0;
	static int videoRenderCalled = 0;
	static int imageRenderCalled = 0;
	
	@BeforeClass
	public static void setup() {
		
		
		// Mocked renderes with Simple callbacks to check render fuction was called on children
		AudioRenderer ar = new mockAudioRenderer(new Runnable() {

			@Override
			public void run() {
				audioRenderCalled++;
			}
		});
		
		VectorRenderer vr = new mockVectorRenderer(new Runnable() {
			@Override
			public void run() {
				vectorRenderCalled++;
			}
		});
		
		TextRenderer tr = new mockTextRenderer(new Runnable() {
			@Override
			public void run() {
				textRenderCalled++;
			}
		});
		
		VideoRenderer mr = new mockVideoRenderer(new Runnable() {
			@Override
			public void run() {
				videoRenderCalled++;
			}
		});
		
		ImageRenderer ir = new mockImageRenderer(new Runnable() {
			@Override
			public void run() {
				imageRenderCalled++;
			}
		});
		
		
		// Create a new sliderenderer using the muck element renderes
		sr = new SlideRenderer(vr,ar,tr,mr, ir);
				
		// Create test slide with one audio element
		audioSlide = new Slide();
		audioSlide.addAudio(new Audio("testData/audioTest.wav"));
		
		
		// create test slide with one shape
		shapeSlide = new Slide();
		shapeSlide.addShape(new Shape("line"));
		
		// create test slide with one text
		textSlide = new Slide();
		Text text = new Text();
		text.setContents(Arrays.asList("test"));
		textSlide.addText(text);
		
		//create test slide with one video
		videoSlide = new Slide();
		videoSlide.addVideo(new Video("local_file.mp4"));;
		
		//create test slide with one image
		imageSlide = new Slide();
		imageSlide.addImage(new Image("local_file.jpg"));;
		
		// create test slide with multiple mixed elements
		multiSlide = new Slide();
		
		multiSlide.addAudio(new Audio("testData/audioTest.wav"));
		multiSlide.addAudio(new Audio("testData/audioTest.wav"));
		
		multiSlide.addVideo(new Video("local_file.mp4"));
		multiSlide.addVideo(new Video("local_file.mp4"));
		multiSlide.addVideo(new Video("local_file.mp4"));
		
		multiSlide.addImage(new Image("local_file.jpg"));
		multiSlide.addImage(new Image("local_file.jpg"));
		
		multiSlide.addShape(new Shape("ellipse"));
		multiSlide.addShape(new Shape("rectangle"));
		
		Text text1 = new Text();
		text.setContents(Arrays.asList("test1"));
		
		Text text2 = new Text();
		text.setContents(Arrays.asList("test2"));
		
		multiSlide.addText(text1);
		multiSlide.addText(text2);


	}
	
	@After
	public void reset(){
		audioRenderCalled=0;
		vectorRenderCalled=0;
		textRenderCalled=0;
		videoRenderCalled=0;
		imageRenderCalled=0;
	}
	
	@Test
	public void test_audio() {
		Node n = sr.render(audioSlide);
		
		assertNotNull(n);
		assertTrue(n instanceof Node);
		assertTrue(n instanceof Pane);
		
		Pane pane = (Pane)n;
		List<Node> children = pane.getChildren();
		
		assertNotNull(children);
		assertEquals(children.size(), 1);
		
		assertTrue(children.get(0) instanceof dummyBox);
		assertEquals(((dummyBox)children.get(0)).name, "audio");

		assertTrue(audioRenderCalled == 1);
	}

	@Test
	public void test_vector() {
		Node n = sr.render(shapeSlide);
		
		assertNotNull(n);
		assertTrue(n instanceof Node);
		assertTrue(n instanceof Pane);
		
		Pane pane = (Pane)n;
		List<Node> children = pane.getChildren();
		
		assertNotNull(children);
		assertEquals(children.size(), 1);
		
		assertTrue(children.get(0) instanceof dummyBox);
		assertEquals(((dummyBox)children.get(0)).name, "shape");
		
		assertTrue(vectorRenderCalled == 1);
	}
	
	@Test
	public void test_text() {
		Node n = sr.render(textSlide);
		
		assertNotNull(n);
		assertTrue(n instanceof Node);
		assertTrue(n instanceof Pane);
		
		Pane pane = (Pane)n;
		List<Node> children = pane.getChildren();
		
		assertNotNull(children);
		assertEquals(children.size(), 1);
		
		assertTrue(children.get(0) instanceof dummyBox);
		assertEquals(((dummyBox)children.get(0)).name, "text");
		
		assertTrue(textRenderCalled == 1);
	}
	
	@Test
	public void test_video() {
		Node n = sr.render(videoSlide);
		
		assertNotNull(n);
		assertTrue(n instanceof Node);
		assertTrue(n instanceof Pane);
		
		Pane pane = (Pane)n;
		List<Node> children = pane.getChildren();
		
		assertNotNull(children);
		assertEquals(children.size(), 1);
		
		assertTrue(children.get(0) instanceof dummyBox);
		assertEquals(((dummyBox)children.get(0)).name, "video");
		
		assertTrue(videoRenderCalled == 1);
	}
	
	@Test
	public void test_image() {
		Node n = sr.render(imageSlide);
		
		assertNotNull(n);
		assertTrue(n instanceof Node);
		assertTrue(n instanceof Pane);
		
		Pane pane = (Pane)n;
		List<Node> children = pane.getChildren();
		
		assertNotNull(children);
		assertEquals(children.size(), 1);
		
		assertTrue(children.get(0) instanceof dummyBox);
		assertEquals(((dummyBox)children.get(0)).name, "image");
		
		assertTrue(imageRenderCalled == 1);
	}
	
	@Test
	public void test_multi() {
		Node n = sr.render(multiSlide);
		
		assertNotNull(n);
		assertTrue(n instanceof Node);
		assertTrue(n instanceof Pane);
		
		Pane pane = (Pane)n;
		List<Node> children = pane.getChildren();
		
		assertNotNull(children);
		assertEquals(children.size(), 11);
		
		int numAudios = 0;
		int numShapes = 0;
		int numTexts = 0;
		int numVideos = 0;
		int numImages = 0;

		for(Node child : children){
			assertTrue(child instanceof dummyBox);
			
			if (((dummyBox)child).name == "audio"){
				numAudios++;
			}
			else if (((dummyBox)child).name == "shape"){
				numShapes++;
			}
			else if (((dummyBox)child).name == "text"){
				numTexts++;
			}
			else if (((dummyBox)child).name == "video"){
				numVideos++;
			}
			else if (((dummyBox)child).name == "image"){
				numImages++;
			}
			else{
				fail();
			}

		}
		
		assertEquals(numAudios, 2);
		assertEquals(numShapes, 2);
		assertEquals(numTexts, 2);
		assertEquals(numVideos, 3);
		assertEquals(numImages, 2);
		
		assertTrue(vectorRenderCalled == 2);
		assertTrue(audioRenderCalled == 2);
		assertTrue(textRenderCalled == 2);
		assertTrue(videoRenderCalled == 3);
		assertTrue(imageRenderCalled == 2);
	}
	
	/**
	 * Testing subclass of audio renderer for mocking/testing purposes
	 */
	private static class mockAudioRenderer extends AudioRenderer {
		
		Runnable r;
		
		public mockAudioRenderer(Runnable r){
			super();

			this.r = r;
		}
		
		@Override
		public Node render(Audio audio){
			// call the callback
			r.run();
			
			// need to return a real node because you cannot add NULL as a child element
			return new dummyBox("audio");
			
		}
	}
	
	private static class mockVectorRenderer extends VectorRenderer {
		
		Runnable r;
		
		public mockVectorRenderer(Runnable r){
			super();

			this.r = r;
		}
		
		@Override
		public Node render(Shape shape){
			// call the callback
			r.run();
			
			// need to return a real node because you cannot add NULL as a child element
			return new dummyBox("shape");
			
		}
	}
	
	private static class mockTextRenderer extends TextRenderer {
		
		Runnable r;
		
		public mockTextRenderer(Runnable r){
			super();

			this.r = r;
		}
		
		@Override
		public Node render(Text text){
			// call the callback
			r.run();
			
			// need to return a real node because you cannot add NULL as a child element
			return new dummyBox("text");
			
		}
	}
	
	/**
	 * Testing subclass of video renderer for mocking/testing purposes
	 */
	private static class mockVideoRenderer extends VideoRenderer {
		
		Runnable r;
		
		public mockVideoRenderer(Runnable r){
			super();

			this.r = r;
		}
		
		@Override
		public Node render(Video video){
			// call the callback
			r.run();
			
			// need to return a real node because you cannot add NULL as a child element
			return new dummyBox("video");
			
		}
	}
	/**
	 * Testing subclass of image renderer for mocking/testing purposes
	 */
	private static class mockImageRenderer extends ImageRenderer {
		
		Runnable r;
		
		public mockImageRenderer(Runnable r){
			super();

			this.r = r;
		}
		
		@Override
		public Node render(Image image){
			// call the callback
			r.run();
			
			// need to return a real node because you cannot add NULL as a child element
			return new dummyBox("image");
			
		}
	}
}
