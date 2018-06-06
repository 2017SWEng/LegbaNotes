package com.legba.notes.renderers;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Assume;
import org.junit.Test;

import com.legba.notes.elements.Video;
import com.legba.notes.models.AppModel;
import com.legba.notes.nodes.MovieView;

public class VideoRendererTest {

	@Test
	public void test() throws URISyntaxException {
		Assume.assumeTrue(System.getProperty( "os.name" ).startsWith( "Windows" ));
		
		File a = new File(VideoRendererTest.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		File b = new File(a.getParent());
		File c = new File(b.getParent());
		AppModel.getInstance().setFile(c.getPath()+File.separator);
		System.out.println("Video path " + c.getPath() + "\\local_file.mp4");
		
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
