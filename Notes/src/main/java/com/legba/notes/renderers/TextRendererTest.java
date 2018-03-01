package com.legba.notes.renderers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import com.legba.notes.elements.*;

import javafx.scene.Node;

import org.junit.Test;

public class TextRendererTest {

	@Test
	public void test() {
		Text text = new Text();
		text.setContents(new ArrayList<Object>(
				Arrays.asList("Hello", "Peter", "Harsh")));
		text.setBold(true);
		
		TextRenderer textRenderer = new TextRenderer();
		
		Node n = textRenderer.render(text);
		assertNotNull(n);
	}

}
