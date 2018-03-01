package com.legba.notes.renderers;

import java.util.ArrayList;

import com.legba.notes.elements.Text;
import javafx.scene.Node;
import javafx.scene.layout.*;

public class TextRenderer {

	public Node render(Text text) {
		ArrayList<javafx.scene.text.Text> words = new ArrayList<javafx.scene.text.Text>();
		
		for (int i=0; i<text.getContents().size(); i++) {
			words.add(new javafx.scene.text.Text((String) text.getContents().get(i)));
		}
		
		HBox n = new HBox();
		
		n.getChildren().addAll(words);
		
		return n;
	}
}


