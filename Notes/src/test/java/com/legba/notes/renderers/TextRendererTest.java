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
	public void testOne() {
		
		Assume.assumeTrue(System.getProperty( "os.name" ).startsWith( "Windows" ));

		TextModel textModel = new TextModel();
		textModel.setContents(new ArrayList<Object>(Arrays.asList("Hello", "Peter", "Harsh")));
		
		textModel.setBold(true);
		textModel.setItalic(true);
		textModel.setUnderline(true);
		
		textModel.setColor(javafx.scene.paint.Color.AZURE);
		textModel.setFill(javafx.scene.paint.Color.RED);
		
		textModel.setTextsize(15);
		
		textModel.setFont("Verdana");
		
		//Sets Horizontal and Vertical Position
		textModel.setX(2f);
		textModel.setY(3f);
		
		TextRenderer textRenderer = new TextRenderer();
		
		HBox renderedText = textRenderer.render(textModel);
		
		if(renderedText != null){
			System.out.println("Node not null");
		}
		
		for(int i = 0; i<3; i++){
			System.out.println(renderedText.getChildren().get(i));
		}
	}
	
	@Test
	public void testTwo() {
		
		Assume.assumeTrue(System.getProperty( "os.name" ).startsWith( "Windows" ));

		TextModel textModel = new TextModel();
		textModel.setContents(new ArrayList<Object>(Arrays.asList("Peter", "Harsh", "Noah", "Sebastian")));
		
		textModel.setBold(false);
		textModel.setItalic(true);
		textModel.setUnderline(false);
		
		textModel.setColor(javafx.scene.paint.Color.AZURE);
		textModel.setFill(javafx.scene.paint.Color.BLUE);
		
		textModel.setTextsize(15);
		
		textModel.setFont("Times New Roman");
		
		//Sets Horizontal and Vertical Position
		textModel.setX(4f);
		textModel.setY(5f);
		
		TextRenderer textRenderer = new TextRenderer();
		
		HBox renderedText = textRenderer.render(textModel);
		
		if(renderedText != null){
			System.out.println("Node not null");
		}
		
		for(int i = 0; i<3; i++){
			System.out.println(renderedText.getChildren().get(i));
		}
	}

}
