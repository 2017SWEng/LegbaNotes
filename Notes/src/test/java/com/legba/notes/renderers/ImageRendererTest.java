package com.legba.notes.renderers;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Assume;
import org.junit.Test;

import com.legba.notes.elements.Image;
import com.legba.notes.nodes.PictureView;

public class ImageRendererTest {

	@Test
	public void test() {
		Assume.assumeTrue(System.getProperty( "os.name" ).startsWith( "Windows" ));
		
		ImageRenderer renderer = new ImageRenderer(); 
		
		//Sets up test image
		Image image = new Image("play2.jpg");
		image.setX(10f);
		image.setX2(30f);
		image.setY(15f);
		image.setY2(40f);
		
		//Renders the test video
		PictureView n = (PictureView) renderer.render(image);

		assertNotNull(n);
		
		//Tests that the rendered image has the same values as set before rendering
		
		//Does not test that the actual image is the same (complicated)
		assertEquals(n.getLayoutX(), 10f, 0.01f);
		assertEquals(n.getLayoutY(), 15f, 0.01f);
		assertEquals(n.getWidth(), 20f, 0.01f);
		assertEquals(n.getHeight(), 25f, 0.01f);
	}

}
