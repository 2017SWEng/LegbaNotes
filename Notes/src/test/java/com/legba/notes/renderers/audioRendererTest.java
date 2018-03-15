package com.legba.notes.renderers;

import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assume;


import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

import com.legba.notes.elements.Audio;
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
	public void test(){
		
		// Don't run this on the CI, because javafx needs a GUI os to run
		Assume.assumeTrue(System.getProperty( "os.name" ).startsWith( "Windows" ));
		
		AudioRenderer audiorend = new AudioRenderer(); 
		Audio audio = new Audio ("testData/audioTest.wav");
		

	
		AudioPlayer n = (AudioPlayer) audiorend.render(audio);
		
		assertNotNull(n);
		
		// label,button,mediaview
		assertEquals(3,n.getChildren().size());
		
					
	}

}
