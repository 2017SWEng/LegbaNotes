package com.legba.notes.renderers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import com.legba.notes.elements.*;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

public class TextRendererTest {

	@Before
	public void setup() throws InterruptedException {
		
		if (!System.getProperty( "os.name" ).startsWith( "Windows" )) {
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
	public void test() {
		
		Assume.assumeTrue(System.getProperty( "os.name" ).startsWith( "Windows" ));

		Text text = new Text();
		text.setContents(new ArrayList<Object>(Arrays.asList("Hello", "Peter", "Harsh")));
		
		text.setBold(true);
		text.setItalic(true);
		text.setUnderline(true);
		
		text.setColor(javafx.scene.paint.Color.AZURE);
		text.setFill(javafx.scene.paint.Color.RED);
		
		text.setTextsize(15);
		
		text.setFont("Verdana");
		
		TextRenderer textRenderer = new TextRenderer();
		
		HBox n = textRenderer.render(text);
		
		if(n != null){
			System.out.println("Node not null");
		}
		
		for(int i = 0; i<3; i++){
			System.out.println(n.getChildren().get(i));
		}
			
		
	}

}
