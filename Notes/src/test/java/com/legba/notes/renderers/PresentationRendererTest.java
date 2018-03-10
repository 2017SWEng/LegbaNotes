package com.legba.notes.renderers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import com.legba.notes.elements.Presentation;
import com.legba.notes.elements.Slide;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Tests for the PresentaionRenderer class
 * @author zraw500
 *
 */
public class PresentationRendererTest {

	static Presentation pres;

	
	static PresentationRenderer pr;
	
	static int slideRenderCalled = 0;
	
	@BeforeClass
	public static void setup() {
		
		// simple mock slide renderer with callback for testing
		SlideRenderer sr = new mockSlideRenderer(new Runnable() {

			@Override
			public void run() {
				slideRenderCalled++;
			}
		});
		
		// make a presentation with the mock slide renderer
		pr = new PresentationRenderer(sr);
				
		pres = new Presentation();
		pres.addSlide(new Slide());
		pres.addSlide(new Slide());
		pres.addSlide(new Slide());

	}
	
	@After
	public void reset(){
		slideRenderCalled=0;
	}
	
	@Test
	public void test() {
		
		if (!System.getProperty( "os.name" ).startsWith( "Windows" )){
			System.out.println("Skipping because OS is not windows : os.name = " + System.getProperty( "os.name" ));
		}
		// Don't run this on the CI, because javafx needs a GUI os to run
		Assume.assumeTrue(System.getProperty( "os.name" ).startsWith( "Windows" ));
		
		Node n = pr.render(pres);
		
		assertNotNull(n);
		assertTrue(n instanceof Node);
		assertTrue(n instanceof ScrollPane);
				
		ScrollPane sp = (ScrollPane)n;
		assertTrue(sp.getContent() instanceof VBox);
		
		VBox box = (VBox)sp.getContent();
		List<Node> children = box.getChildren();
		
		assertNotNull(children);
		assertEquals(children.size(), 3);
				
		assertTrue(children.get(0) instanceof dummyBox);
		assertEquals(((dummyBox)children.get(0)).name, "slide");

		assertTrue(slideRenderCalled == 3);
	}

	

	private static class mockSlideRenderer extends SlideRenderer {
		
		Runnable r;
		
		public mockSlideRenderer(Runnable r){
			super();
	
			this.r = r;
		}
		
		@Override
		public Node render(Slide slide){
			// call the callback
			r.run();
			// need to return a real node because you cannot add NULL as a child element
			return new dummyBox("slide");
			
		}
	}
}
