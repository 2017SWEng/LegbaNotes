package com.legba.notes.renderers;

import java.util.ArrayList;

import com.legba.notes.elements.Br;
import com.legba.notes.elements.Format;
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
			
			// Handles Type Strings
			if(textModel.getContents().get(i) instanceof String){
				javafx.scene.text.Text renderedString = stringRenderer((String)textModel.getContents().get(i), textModel);
				words.add(renderedString);
			//Handles type Format
			}else if(textModel.getContents().get(i) instanceof Format){
				javafx.scene.text.Text renderedFormat = formatRenderer((Format)textModel.getContents().get(i));
				words.add(renderedFormat);
			//Handles type Break
			}else if(textModel.getContents().get(i) instanceof Br){
				javafx.scene.text.Text renderedBreak = breakRenderer((Br)textModel.getContents().get(i));
				words.add(renderedBreak);
			}else {
				System.err.println("Passed unknown class in text model renderer");
			}
		}
		
		//Creates a HBox to wrap our JavaFX Text object in
		HBox n = new HBox();
		n.getChildren().addAll(words);
		
		//Returns the HBox as a Node? That doesn't seem right
		return n;
	
	}


	public javafx.scene.text.Text stringRenderer(String string, TextModel textModel) {
		
		javafx.scene.text.Text text = new javafx.scene.text.Text();
		
		text.setText(string);
				
		text.setFont(Font.font(
			// Checking font type
			textModel.getFont() == null ? DEFAULT_Font : textModel.getFont(), 
			textModel.getTextsize() == null ? DEFAULT_Textsize : textModel.getTextsize())
		);
		
		//Color of text 
		text.setFill(
			textModel.getFill() == null ? DEFAULT_Fill : textModel.getFill()
		);//Can only test fill since color is background not foreground.
		
		
		text.setUnderline(
			textModel.getUnderline() == null ? DEFAULT_Underline : textModel.getUnderline()
		);
		
		// Checking Bold and Italic.
		boolean isItalic = textModel.getItalic() == null ? DEFAULT_Italic : textModel.getItalic();
		boolean isBold = textModel.getBold() == null ? DEFAULT_Bold : textModel.getBold();
		
		text.setFont(Font.font(
			textModel.getFont() == null ? DEFAULT_Font : textModel.getFont(), 
			isBold == false ? FontWeight.NORMAL : FontWeight.BOLD,
			isItalic == false ? FontPosture.REGULAR : FontPosture.ITALIC,
			textModel.getTextsize() == null ? DEFAULT_Textsize : textModel.getTextsize())
		);
		
		//Position
		text.setX(textModel.getX() == null ? DEFAULT_X : textModel.getX());
		text.setY(textModel.getY() == null ? DEFAULT_Y : textModel.getY());
		
		return text;
	
	}
	
	public javafx.scene.text.Text formatRenderer(Format format){
		javafx.scene.text.Text text = new javafx.scene.text.Text();
		text.setText(format.getText());
		
		text.setFont(Font.font(
			// Checking font type
			format.getFont() == null ? DEFAULT_Font : format.getFont(), 
			format.getTextsize() == null ? DEFAULT_Textsize : format.getTextsize())
		);
		
		// Color of text  
		text.setFill(
			format.getFill() == null ? DEFAULT_Fill : format.getFill()
		);//Can only test fill since color is background not foreground.
		
		
		text.setUnderline(
			format.getUnderline() == null ? DEFAULT_Underline : format.getUnderline()
		);
		
		//Checking Bold and Italic.
		boolean isItalic = format.getItalic() == null ? DEFAULT_Italic : format.getItalic();
		boolean isBold = format.getBold() == null ? DEFAULT_Bold : format.getBold();

		text.setFont(Font.font(
				format.getFont() == null ? DEFAULT_Font : format.getFont(), 
				isBold == false ? FontWeight.NORMAL : FontWeight.BOLD,
				isItalic == false ? FontPosture.REGULAR : FontPosture.ITALIC,
				format.getTextsize() == null ? DEFAULT_Textsize : format.getTextsize())
			);
		
		return text;
	}
	
	public javafx.scene.text.Text breakRenderer(Br Break){
		javafx.scene.text.Text text = new javafx.scene.text.Text();
		text.setText("\n");
		return text;
	}
}




