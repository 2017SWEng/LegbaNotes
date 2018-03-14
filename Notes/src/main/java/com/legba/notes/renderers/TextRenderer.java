package com.legba.notes.renderers;

import java.util.ArrayList;

import com.legba.notes.elements.Br;
import com.legba.notes.elements.Format;
import com.legba.notes.elements.Text;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class TextRenderer extends Renderer<Text> {
	
	// Set Default values
	private final boolean DEFAULT_Italic = false;
	private final boolean DEFAULT_Bold = false;
	private final boolean DEFAULT_Underline = false;
	
	private final Color DEFAULT_Fill = javafx.scene.paint.Color.BLACK;
	private final Integer DEFAULT_Textsize = 10;
	private final String DEFAULT_Font = "Times New Roman";
	private final float DEFAULT_X = 0f;
	private final float DEFAULT_Y = 0f;
	
	/**
	 * Renderer for Text elements
	 * @param textModel model for text elements
	 * @return javafx node that represents the text model
	 */
	public Node render(Text textModel) {
		
		//Create an Array of JavaFX Text objects that our text .
		//objects will be stored into
		ArrayList<javafx.scene.text.Text> lines = new ArrayList<javafx.scene.text.Text>();
	
		//For each line in the text model apply the correct renderer
		for (int i=0; i<textModel.getContents().size(); i++) {
			
			if (textModel.getContents().get(i) instanceof String){
				javafx.scene.text.Text renderedString = stringRenderer((String)textModel.getContents().get(i), textModel);
				lines.add(renderedString);
			}
			else if (textModel.getContents().get(i) instanceof Format){
				javafx.scene.text.Text renderedFormat = formatRenderer((Format)textModel.getContents().get(i));
				lines.add(renderedFormat);
			}
			else if (textModel.getContents().get(i) instanceof Br){
				javafx.scene.text.Text renderedBreak = breakRenderer((Br)textModel.getContents().get(i));
				lines.add(renderedBreak);
			}
			else {
				System.err.println("Passed unknown class in text model renderer");
			}
		}
		
		
		FlowPane flow = new FlowPane();
		flow.getChildren().addAll(lines);
		return flow;
		
	}

	/**
	 * Renderer for strings within Text model
	 * @param string the text data
	 * @param textModel model contains formatting data
	 * @return
	 */
	private javafx.scene.text.Text stringRenderer(String string, Text textModel) {
		
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
	
	/**
	 * Renderer for formatted text within a Text element
	 * @param format Formatted text model
	 * @return a javafx Text element that represents a subsection of formatted text 
	 */
	private javafx.scene.text.Text formatRenderer(Format format){
		javafx.scene.text.Text text = new javafx.scene.text.Text();
		text.setText(format.getText());
		
		text.setFont(Font.font(
			// Checking font type
			format.getFont() == null ? DEFAULT_Font : format.getFont(), 
			format.getTextsize() == null ? DEFAULT_Textsize : format.getTextsize())
		);
		
		//Color of text  
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
	
	/**
	 * Renderer for break elements. These act as line breaks as defined in the pws
	 * @param Break Line break model
	 * @return javafx text object that acts as a line break
	 */
	private javafx.scene.text.Text breakRenderer(Br Break){
		javafx.scene.text.Text text = new javafx.scene.text.Text();
		text.setText("\n");
		return text;
	}
}




