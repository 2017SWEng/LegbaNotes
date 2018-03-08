package com.legba.notes.renderers;

import java.util.ArrayList;

import com.legba.notes.elements.Text;
import javafx.scene.Node;
import javafx.scene.layout.*;

public class TextRenderer {

	public HBox render(Text text) {
		//Create an Array of JavaFX Text objects that our text 
		//objects will be stored into
		ArrayList<javafx.scene.text.Text> words = new ArrayList<javafx.scene.text.Text>();
	
		//Store each "word" from our text object into the array of 
		//JavaFX Text objects
		for (int i=0; i<text.getContents().size(); i++) {
			words.add(new javafx.scene.text.Text((String) text.getContents().get(i)));
		}
		
		//Creates a HBox to wrap our JavaFX Text object in
		HBox n = new HBox();
		n.getChildren().addAll(words);
		
		//Returns the HBox as a Node? That doesn't seem right
		return n;
	}
}


