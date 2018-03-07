package com.legba.notes.renderers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import org.junit.Test;

import com.legba.notes.elements.Audio;
import com.legba.notes.elements.Shape;
import com.legba.notes.elements.Slide;


import org.junit.BeforeClass;

public class SlideRendererTest {

	static Slide audioSlide;
	static Slide shapeSlide;
	static Slide multiSlide;

	
	static SlideRenderer sr;
	
	static int audioRenderCalled = 0;
	static int vectorRenderCalled = 0;
	
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
		//TODO: add other renderers
		
		
		// Create a new sliderenderer using the muck element renderes
		sr = new SlideRenderer(vr,ar);
				
		// Create test slide with one audio element
		audioSlide = new Slide();
		audioSlide.addAudio(new Audio("testData/audioTest.wav"));
		
		
		// create test slide with one shape
		shapeSlide = new Slide();
		shapeSlide.addShape(new Shape("line"));
		
		// create test slide with multiple mixed elements
		multiSlide = new Slide();
		multiSlide.addAudio(new Audio("testData/audioTest.wav"));
		multiSlide.addAudio(new Audio("testData/audioTest.wav"));
		multiSlide.addShape(new Shape("ellipse"));
		multiSlide.addShape(new Shape("rectangle"));

	}
	
	@After
	public void reset(){
		audioRenderCalled=0;
		vectorRenderCalled=0;
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
	public void test_multi() {
		Node n = sr.render(multiSlide);
		
		assertNotNull(n);
		assertTrue(n instanceof Node);
		assertTrue(n instanceof Pane);
		
		Pane pane = (Pane)n;
		List<Node> children = pane.getChildren();
		
		assertNotNull(children);
		assertEquals(children.size(), 4);
		
		int numAudios = 0;
		int numShapes = 0;
		for(Node child : children){
			assertTrue(child instanceof dummyBox);
			
			if (((dummyBox)child).name == "audio"){
				numAudios++;
			}
			else if (((dummyBox)child).name == "shape"){
				numShapes++;
			}
			else{
				fail();
			}

		}
		
		assertEquals(numAudios, 2);
		assertEquals(numShapes, 2);
		
		assertTrue(vectorRenderCalled == 2);
		assertTrue(audioRenderCalled == 2);
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
}
