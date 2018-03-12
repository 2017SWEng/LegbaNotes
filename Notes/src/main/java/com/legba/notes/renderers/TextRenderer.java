package com.legba.notes.renderers;

import java.util.ArrayList;

import com.legba.notes.elements.TextModel;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class TextRenderer {
	
	// Set Default values
	private final boolean DEFAULT_Italic = false;
	private final boolean DEFAULT_Bold = false;
	private final boolean DEFAULT_Underline = false;
	
	private final Color DEFAULT_Fill = javafx.scene.paint.Color.BLACK;
	private final Integer DEFAULT_Textsize = 10;
	private final String DEFAULT_Font = "Times New Roman";
	private final float DEFAULT_X = 0f;
	private final float DEFAULT_Y = 0f;
	

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
			words.get(i).setFont(Font.font(textModel.getFont() == null ? DEFAULT_Font : textModel.getFont(), 
								 textModel.getTextsize() == null ? DEFAULT_Textsize : textModel.getTextsize())
								);
			// Testing Color of text which is foreground. 
			words.get(i).setFill(textModel.getFill() == null ? DEFAULT_Fill : textModel.getFill());
			//Can only test fill since color is background.
			
			// This test should work but can't find a way to check.
			words.get(i).setUnderline(textModel.getUnderline() == null ? DEFAULT_Underline : textModel.getUnderline());
			
			// Testing Bold and Italic.
			boolean isItalic = textModel.getItalic() == null ? DEFAULT_Italic : textModel.getItalic();
			boolean isBold = textModel.getBold() == null ? DEFAULT_Bold : textModel.getBold();
			
			
			words.get(i).setFont(Font.font(textModel.getFont() == null ? DEFAULT_Font : textModel.getFont(), 
											isBold == false ? FontWeight.NORMAL : FontWeight.BOLD,
											isItalic == false ? FontPosture.REGULAR : FontPosture.ITALIC,
											textModel.getTextsize() == null ? DEFAULT_Textsize : textModel.getTextsize())
									);
				
		}
		for (int i = 0; i<words.size(); i++) {
			// Grabs every word in Array and sets its position
			javafx.scene.text.Text PositionTest = words.get(i);
			PositionTest.setX(textModel.getX() == null ? DEFAULT_X : textModel.getX());
			PositionTest.setY(textModel.getY() == null ? DEFAULT_Y : textModel.getY());
		} 
				
		//Creates a HBox to wrap our JavaFX Text object in
		HBox n = new HBox();
		n.getChildren().addAll(words);
		
		//Returns the HBox as a Node? That doesn't seem right
		return n;
	
	}
	}


