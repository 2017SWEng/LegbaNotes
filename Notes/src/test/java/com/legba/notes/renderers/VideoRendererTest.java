package com.legba.notes.renderers;

import static org.junit.Assert.*;

import org.junit.Assume;
import org.junit.Test;

import com.legba.notes.elements.Video;
import com.legba.notes.nodes.MovieView;

public class VideoRendererTest {

	@Test
	public void test() {
		Assume.assumeTrue(System.getProperty( "os.name" ).startsWith( "Windows" ));
		
		VideoRenderer renderer = new VideoRenderer(); 
		
		//Sets up test video
		Video video = new Video("local_file.mp4");
		video.setX(10f);
		video.setX2(30f);
		video.setY(15f);
		video.setY2(40f);
		
		//Renders the test video
		MovieView n = (MovieView) renderer.render(video);
		
		
		assertNotNull(n);
		
		//Tests that the rendered video has the same values as set before rendering
		assertTrue(n.getMediaPlayer().getMedia().getSource().toString().endsWith("local_file.mp4"));
		assertEquals(n.getLayoutX(), 10f, 0.01f);
		assertEquals(n.getLayoutY(), 15f, 0.01f);
		assertEquals(n.getWidth(), 20f, 0.01f);
		assertEquals(n.getHeight(), 25f, 0.01f);
	}

}
