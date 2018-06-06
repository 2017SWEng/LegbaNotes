package com.legba.notes.renderers;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;
import javax.swing.SwingUtilities;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assume;

import java.io.File;
import java.net.URISyntaxException;

import javafx.embed.swing.JFXPanel;


import com.legba.notes.elements.Audio;
import com.legba.notes.models.AppModel;
import com.legba.notes.nodes.AudioPlayer;
import com.legba.notes.renderers.AudioRenderer;

public class audioRendererTest {

	@Before
	public void setup() throws InterruptedException {
		
		if (!System.getProperty( "os.name" ).startsWith( "Windows" )){
			System.out.println("Skipping because OS is not windows : os.name = " + System.getProperty( "os.name" ));
		}
		// Don't run this on the CI, because javafx needs a GUI os to run
		Assume.assumeTrue(System.getProperty( "os.name" ).startsWith( "Windows" ));
		
		// JavaFX is stupid, cannot create a Text item without a scene
		// Here were using a Swing embed JFX panel to create one
		// This is totally a hack, but it works so...
		final CountDownLatch latch = new CountDownLatch(1);
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        new JFXPanel(); // initializes JavaFX environment
		        latch.countDown();
		    }
		});
		latch.await();
}
	
	@Test
	public void test() throws URISyntaxException{
		File a = new File(audioRendererTest.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		File b = new File(a.getParent());
		File c = new File(b.getParent());
		AppModel.getInstance().setFile(c.getPath()+File.separator);
		System.out.println("AUdio path " + c.getPath() + "\\audioTest.wav");

		// Don't run this on the CI, because javafx needs a GUI os to run
		Assume.assumeTrue(System.getProperty( "os.name" ).startsWith( "Windows" ));
		
		AudioRenderer audiorend = new AudioRenderer(); 
		Audio audio = new Audio ("audioTest.wav");
		

	
		AudioPlayer n = (AudioPlayer) audiorend.render(audio);
		
		assertNotNull(n);
		
		// label,button,mediaview
		assertEquals(2,n.getChildren().size());
		
					
	}

}
