package com.legba.notes.renderers;

import java.util.ArrayList;

import com.legba.notes.elements.TextModel;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.text.Font;

public class TextRenderer {

	public HBox render(TextModel textModel) {
		//Create an Array of JavaFX Text objects that our text .
		//objects will be stored into
		ArrayList<javafx.scene.text.Text> words = new ArrayList<javafx.scene.text.Text>();
	
		//Store each "word" from our text object into the array of 
		//JavaFX Text objects
		for (int i=0; i<textModel.getContents().size(); i++) {
			words.add(new javafx.scene.text.Text((String) textModel.getContents().get(i)));
		}
		//
		
		for (int i = 0; i<words.size(); i++) {
			// Test if loop runs
			System.out.println("Loop running for array word:" + i);
			
			// Testing all font based attributes
			words.get(i).setFont(Font.font(textModel.getFont(), 
								 textModel.getTextsize())
								);
			// Testing Color of text which is foreground. 
			words.get(i).setFill(textModel.getFill());
			//Can only test fill since color is background.
			
			// This test should work but can't find a way to check.
			words.get(i).setUnderline(textModel.getUnderline());
			
			// Testing Bold and Italic.
			if(textModel.getItalic() == true){
				words.get(i).setFont(Font.font(textModel.getFont(), 
												FontPosture.ITALIC,
												textModel.getTextsize())
									);
				}
			if(textModel.getBold() == true){
				words.get(i).setFont(Font.font(textModel.getFont(), 
												FontWeight.BOLD,
												textModel.getTextsize())
									);
			}
			if(textModel.getBold() == true && textModel.getItalic() == true){
				words.get(i).setFont(Font.font(textModel.getFont(), 
									 FontWeight.BOLD,
									 FontPosture.ITALIC, 
									 textModel.getTextsize())
									 );
				}
		}
		for (int i = 0; i<words.size(); i++) {
			// Grabs every word in Array and sets its position
			javafx.scene.text.Text PositionTest = words.get(i);
			PositionTest.setX(textModel.getX());
			PositionTest.setY(textModel.getY());;
		} 
				
		//Creates a HBox to wrap our JavaFX Text object in
		HBox n = new HBox();
		n.getChildren().addAll(words);
		
		//Returns the HBox as a Node? That doesn't seem right
		return n;
	
	}
	}


