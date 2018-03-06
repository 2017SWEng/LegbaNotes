package com.legba.notes.renderers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
		
		sr = new SlideRenderer(vr,ar);
				
		audioSlide = new Slide();
		audioSlide.addAudio(new Audio("testData/audioTest.wav"));
		
		
		shapeSlide = new Slide();
		shapeSlide.addShape(new Shape("line"));
		
		multiSlide = new Slide();
		multiSlide.addAudio(new Audio("testData/audioTest.wav"));
		multiSlide.addAudio(new Audio("testData/audioTest.wav"));
		multiSlide.addShape(new Shape("line"));
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
	
	private static class mockAudioRenderer extends AudioRenderer {
		
		Runnable r;
		
		public mockAudioRenderer(Runnable r){
			super();

			this.r = r;
		}
		
		@Override
		public Node render(Audio audio){
			r.run();
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
			r.run();
			
			// Return a dummy Node
			return new dummyBox("shape");
			
		}
	}
	
	private static class dummyBox extends VBox {
		
		public String name;
		public dummyBox(String name){
			this.name = name;
		}
	}
}
