package com.legba.notes.renderers;

import java.util.ArrayList;

import com.legba.notes.elements.Br;
import com.legba.notes.elements.Format;
import com.legba.notes.elements.Text;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextFlow;

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
	private final float DEFAULT_WIDTH = 1000f;
	private final float DEFAULT_HEIGHT = 200f;
	
	/**
	 * Renderer for Text elements
	 * @param textModel model for text elements
	 * @return javafx node that represents the text model
	 */
	public Node render(Text textModel) {
		
		//Create an Array of JavaFX Text objects that our text.
		//objects will be stored into
		ArrayList<javafx.scene.text.Text> lines = new ArrayList<javafx.scene.text.Text>();
		
	
		//For each line in the text model apply the correct renderer
		for (int i=0; i<textModel.getContents().size(); i++) {
			
			if (textModel.getContents().get(i) instanceof String){
				javafx.scene.text.Text renderedString = stringRenderer((String)textModel.getContents().get(i), textModel);
				if (renderedString != null) {
					lines.add(renderedString);
				}
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

		TextFlow flow = new TextFlow();
		flow.getChildren().addAll(lines);
		
		
		flow.setLayoutX(textModel.getX() == null ? DEFAULT_X : textModel.getX());
		flow.setLayoutY(textModel.getY() == null ? DEFAULT_Y : textModel.getY());
		flow.setMinWidth(textModel.getWidth() == null ? DEFAULT_WIDTH : textModel.getWidth());
		flow.setMinHeight(textModel.getHeight() == null ? DEFAULT_HEIGHT : textModel.getHeight());
		
		//System.out.println(flow.getLayoutBounds());
		//System.out.println(flow.getChildren().toString());
		
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
		
		if (string.isEmpty()) {
			return null;
		}
		
		Paint fill;
		if (textModel.getFill() == null) {
			fill = Paint.valueOf(DEFAULT_Fill.toString());
		}
		else if (textModel.getFill() instanceof LinearGradient) {
			Stop[] stops = new Stop[] {new Stop(0, ((LinearGradient) textModel.getFill()).getStops().get(0).getColor()), new Stop(1, ((LinearGradient) textModel.getFill()).getStops().get(1).getColor())};
			textModel.setFill(new LinearGradient(text.getX(), text.getY(), text.getLayoutBounds().getWidth(), text.getLayoutBounds().getHeight(), false, CycleMethod.NO_CYCLE, stops));
			fill = textModel.getFill();
		}
		else {
			fill = textModel.getFill();
		}
		
		StringProperty style = new SimpleStringProperty();
		BooleanProperty underline = new SimpleBooleanProperty();
		FloatProperty x = new SimpleFloatProperty();
		FloatProperty y = new SimpleFloatProperty();
		ObjectProperty<Paint> paintFill = new SimpleObjectProperty<Paint>();
		
		text.setText(string.trim());

		text.setStyle(textModel.getStyle());
		
		text.setUnderline(textModel.getUnderline() == null ? DEFAULT_Underline : textModel.getUnderline());
		
		text.setFill(fill);
		
		
		//Position
		text.setX(textModel.getX() == null ? DEFAULT_X : textModel.getX());
		text.setY(textModel.getY() == null ? DEFAULT_Y : textModel.getY());
		
		if (textModel.styleProperty()!= null) {
			text.styleProperty().bind(style);
			style.bind(textModel.styleProperty());
		}
		
		if (textModel.xProperty()!= null) {
			text.xProperty().bind(x);
			x.bind(textModel.xProperty());
		}
		
		if (textModel.yProperty() != null) {
			text.yProperty().bind(y);
			y.bind(textModel.yProperty());
		}
		
		if (textModel.underlineProperty() != null) {
			text.underlineProperty().bind(underline);
			underline.bind(textModel.underlineProperty());
		}
		
		if (textModel.paintFillProperty() != null) {
			text.fillProperty().bind(paintFill);
			paintFill.bind(textModel.paintFillProperty());
		}
		
		return text;
	
	}
	
	/**
	 * Renderer for formatted text within a Text element
	 * @param format Formatted text model
	 * @return a javafx Text element that represents a subsection of formatted text 
	 */
	private javafx.scene.text.Text formatRenderer(Format format){
		javafx.scene.text.Text text = new javafx.scene.text.Text();
		
		Paint fill;
		if (format.getFill() == null) {
			fill = Paint.valueOf(DEFAULT_Fill.toString());
		}
		else if (format.getFill() instanceof LinearGradient) {
			Stop[] stops = new Stop[] {new Stop(0, ((LinearGradient) format.getFill()).getStops().get(0).getColor()), new Stop(1, ((LinearGradient) format.getFill()).getStops().get(1).getColor())};
			format.setFill(new LinearGradient(text.getX(), text.getY(), text.getLayoutBounds().getWidth(), text.getLayoutBounds().getHeight(), false, CycleMethod.NO_CYCLE, stops));
			fill = format.getFill();
		}
		else {
			fill = format.getFill();
		}
		
		
		
		text.setText(format.getText());
		
		text.setStyle(format.createCSSStyle(format));
		
		text.setFill(fill);
		
		text.setUnderline(format.getUnderline() == null ? DEFAULT_Underline : format.getUnderline());
		
		StringProperty style = new SimpleStringProperty();
		ObjectProperty<Paint> paintFill = new SimpleObjectProperty<Paint>();
		BooleanProperty underline = new SimpleBooleanProperty();
		

		
		if (format.styleProperty()!= null) {
			text.styleProperty().bind(style);
			style.bind(format.styleProperty());
		}
		
		if (format.underlineProperty() != null) {
			text.underlineProperty().bind(underline);
			underline.bind(format.underlineProperty());
		}
		
		if (format.paintFillProperty() != null) {
			text.fillProperty().bind(paintFill);
			paintFill.bind(format.paintFillProperty());
		}
		
		
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




