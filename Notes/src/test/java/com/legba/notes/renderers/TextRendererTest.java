package com.legba.notes.renderers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import com.legba.notes.elements.*;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.HBox;

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

		Text textModel = new Text();
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
		
		HBox renderedText = (HBox) textRenderer.render(textModel);
		
		assertNotNull(renderedText);
		assertNotNull(renderedText.getChildren());
		
		assertEquals(renderedText.getChildren().size(), 3);
		
		// Can loop because is true for all contents
		for(int i = 0; i<renderedText.getChildren().size(); i++){
			assertTrue(renderedText.getChildren().get(i) instanceof javafx.scene.text.Text);
			assertTrue(((javafx.scene.text.Text)renderedText.getChildren().get(i)).getFont().getStyle().contains("Bold"));
			assertTrue(((javafx.scene.text.Text)renderedText.getChildren().get(i)).getFont().getStyle().contains("Italic"));
			assertTrue(((javafx.scene.text.Text)renderedText.getChildren().get(i)).isUnderline());
			assertTrue(((javafx.scene.text.Text)renderedText.getChildren().get(i)).getFont().getFamily().contains("Verdana"));
			assertEquals(((javafx.scene.text.Text)renderedText.getChildren().get(i)).getFont().getSize(), 15f, 0.01f);
			assertEquals(((javafx.scene.text.Text)renderedText.getChildren().get(i)).getX(), 2f, 0.01f);
			assertEquals(((javafx.scene.text.Text)renderedText.getChildren().get(i)).getY(), 3f, 0.01f);
		}
		
		//Check the contents
		assertEquals("Hello", ((javafx.scene.text.Text)renderedText.getChildren().get(0)).getText());
		assertEquals("Peter", ((javafx.scene.text.Text)renderedText.getChildren().get(1)).getText());
		assertEquals("Harsh", ((javafx.scene.text.Text)renderedText.getChildren().get(2)).getText());
		
	}
	
	@Test
	public void testTwo() {
		
		Assume.assumeTrue(System.getProperty( "os.name" ).startsWith( "Windows" ));

		Text textModel = new Text();
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
		
		HBox renderedText = (HBox) textRenderer.render(textModel);
		
		
		assertNotNull(renderedText);
		assertNotNull(renderedText.getChildren());
		
		assertEquals(renderedText.getChildren().size(), 4);
		
		// Can loop because is true for all contents
		for(int i = 0; i<renderedText.getChildren().size(); i++){
			assertTrue(renderedText.getChildren().get(i) instanceof javafx.scene.text.Text);
			assertTrue(((javafx.scene.text.Text)renderedText.getChildren().get(i)).getFont().getStyle().contains("Italic"));
			assertTrue(((javafx.scene.text.Text)renderedText.getChildren().get(i)).getFont().getFamily().contains("Times New Roman"));
			assertEquals(((javafx.scene.text.Text)renderedText.getChildren().get(i)).getFont().getSize(), 15f, 0.01f);
			assertEquals(((javafx.scene.text.Text)renderedText.getChildren().get(i)).getX(), 4f, 0.01f);
			assertEquals(((javafx.scene.text.Text)renderedText.getChildren().get(i)).getY(), 5f, 0.01f);
		}
		
		assertEquals("Peter", ((javafx.scene.text.Text)renderedText.getChildren().get(0)).getText());
		assertEquals("Harsh", ((javafx.scene.text.Text)renderedText.getChildren().get(1)).getText());
		assertEquals("Noah", ((javafx.scene.text.Text)renderedText.getChildren().get(2)).getText());
		assertEquals("Sebastian", ((javafx.scene.text.Text)renderedText.getChildren().get(3)).getText());
		
	}
	
	public void testThree() {
		
		Assume.assumeTrue(System.getProperty( "os.name" ).startsWith( "Windows" ));

		Format format = new Format();
		format.setBold(true);
		format.setText("banana");
		
		Text textModel = new Text();
		textModel.setContents(
			new ArrayList<Object>(
				Arrays.asList(
					"Hello",
					new Br(),
					"apple",
					format,
					"test"
				)
			)
		);
		
		
		TextRenderer textRenderer = new TextRenderer();
		
		HBox renderedText = (HBox) textRenderer.render(textModel);
		
		assertNotNull(renderedText);
		assertNotNull(renderedText.getChildren());
		assertEquals(renderedText.getChildren().size(), 5);
		
		assertTrue(renderedText.getChildren().get(0) instanceof javafx.scene.text.Text);
		assertEquals(((javafx.scene.text.Text)renderedText.getChildren().get(0)).getText(), "Hello");
		
		assertTrue(renderedText.getChildren().get(1) instanceof javafx.scene.text.Text);
		assertEquals(((javafx.scene.text.Text)renderedText.getChildren().get(1)).getText(), "\n");
		
		assertTrue(renderedText.getChildren().get(2) instanceof javafx.scene.text.Text);
		assertEquals(((javafx.scene.text.Text)renderedText.getChildren().get(2)).getText(), "apple");
		
		assertTrue(renderedText.getChildren().get(3) instanceof javafx.scene.text.Text);
		assertEquals(((javafx.scene.text.Text)renderedText.getChildren().get(3)).getText(), "banana");
		assertTrue(((javafx.scene.text.Text)renderedText.getChildren().get(3)).getFont().getStyle().contains("Bold"));

		assertTrue(renderedText.getChildren().get(4) instanceof javafx.scene.text.Text);
		assertEquals(((javafx.scene.text.Text)renderedText.getChildren().get(4)).getText(), "test");
		

	}
	
	@Test
	public void testNull() {
		
		Assume.assumeTrue(System.getProperty( "os.name" ).startsWith( "Windows" ));

		Text textModel = new Text();
		textModel.setContents(new ArrayList<Object>(Arrays.asList("Peter", "Harsh", "Noah", "Sebastian", "Robert")));
			
		TextRenderer textRenderer = new TextRenderer();
		
		HBox renderedText = (HBox) textRenderer.render(textModel);
		
		assertNotNull(renderedText);
		assertNotNull(renderedText.getChildren());
		assertEquals(renderedText.getChildren().size(), 5);
		
	}

}
