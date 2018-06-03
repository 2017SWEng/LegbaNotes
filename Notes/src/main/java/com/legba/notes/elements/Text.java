package com.legba.notes.elements;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.legba.notes.elements.base.*;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
/**
 * Class is the text element and allows the formatting 
 * of text elements parameters such as setting bold, italic etc...
 * Class extends SlideElement and implements 
 * Transitionable, Colorable and Formatable.
 * If any parameter is set to null its state will not change.
 * @author vc622
 */
//Rich text element
public class Text extends SlideElement implements Transitionable,Colorable,Formatable{
	
	private final boolean DEFAULT_Italic = false;
	private final boolean DEFAULT_Bold = false;
	private final boolean DEFAULT_Underline = false;
	private final Integer DEFAULT_Textsize = 10;
	private final String DEFAULT_Font = "Times New Roman";
	
	private Integer start;
	
	private Integer duration;
	
	private ObjectProperty<Paint> paintColor;
	
	private ObjectProperty<Paint> paintFill;
	
	private String font;
	
	private Boolean italic;
	
	private Boolean bold;
	
	private BooleanProperty underline;
	
	private Integer textsize;
	
	private StringProperty style;
	

	private List<Object> contents;

	public Text() {
		super();		
		this.contents = new ArrayList<Object>();

		setStyle(createCSSStyle(this));

	}
	
	public Text(String s) {
		super();
		
		this.color = javafx.scene.paint.Color.BLACK;
		this.fill = javafx.scene.paint.Color.BLACK;
		this.font = "Times New Roman";
		this.italic = false;
		this.bold = false;
		this.underline = false;
		this.textsize = 10;
		this.contents = new ArrayList<Object>();
		this.contents.add(s);
		
		setStyle(createCSSStyle(this));

	}
	
	
	@XmlElementRefs({
    	@XmlElementRef(name="Format", type=Format.class),
    	@XmlElementRef(name="Br", type=Br.class)
	})
	@XmlMixed
	public List<Object> getContents() {
		return this.contents;
	}
	/**
	 * Sets the contents of Text
	 * @param List<Object> contents
	 */
	public void setContents(List<Object> contents) {
		if (contents == null) {
			return;
		}
		this.contents=contents;		
	}
	
	public void addContents(Object content) {
		this.contents.clear();
		this.contents.add(content);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<this.getContents().size(); i++) {
			
			if (this.getContents().get(i) instanceof String){
				sb.append(this.getContents().get(i));
			}
			else if (this.getContents().get(i) instanceof Format){
				sb.append(((Format)this.getContents().get(i)).getText());
			}
			else if (this.getContents().get(i) instanceof Br){
				sb.append("\n");
			}
			else {
				System.err.println("Error");
			}
		}
		return sb.toString();
	}
	
	@Override
	@XmlAttribute
	public String getFont() {
		return this.font;
	}

	@Override
	/**
	 * Sets Font of Text
	 * @param font
	 */
	public void setFont(String font) {
		if (font == null) {
			return;
		}
		this.font=font;		
		setStyle(createCSSStyle(this));
	}


	@Override
	@XmlAttribute
	public Boolean getItalic() {
		return this.italic;
	}


	@Override
	/**
	 * Sets text to Italic
	 * @param italic
	 */
	public void setItalic(Boolean italic) {
		if (italic == null) {
			return;
		}
		this.italic=italic;
		setStyle(createCSSStyle(this));
	}


	@Override
	@XmlAttribute
	public Boolean getBold() {
		return this.bold;
	}


	@Override
	/**
	 * Sets text to Bold
	 * @param bold
	 */
	public void setBold(Boolean bold) {
		if (bold == null) {
			return;
		}
		this.bold=bold;
		setStyle(createCSSStyle(this));
	}
	
	@XmlAttribute
	public Boolean getUnderline() {
		return (underline == null) ? null : underline.get();
	}
	
	//Creates a property for stroke
	public BooleanProperty underlineProperty() {
		return this.underline;
	}


	@Override
	/**
	 * Underlines Text
	 * @param underline
	 */
	public void setUnderline(Boolean newUnderline) {
		if(newUnderline == null){
			underline = null;
			return;
		}
		else if (underline == null){
			underline = new SimpleBooleanProperty();
		}
		
		underline.set(newUnderline);
		setStyle(createCSSStyle(this));
	}


	@Override
	@XmlAttribute
	public Integer getTextsize() {
		return this.textsize;
	}


	@Override
	/**
	 *Sets size of Text
	 * @param underline
	 */
	public void setTextsize(Integer size) {
		if (size == null) {
			return;
		}

		this.textsize=size;	
		setStyle(createCSSStyle(this));
	}
		
	
	//Returns the colour of the outline
		@Override
	    @XmlJavaTypeAdapter(ColorAdapter.class)
		@XmlAttribute
		public Paint getColor() {
			return (paintColor == null) ? null : paintColor.get();
		}
		
		public ObjectProperty<Paint> paintColorProperty() {
			return this.paintColor;	
		}
		
		//Sets colour of outline if newColor is not null
		@Override
		public void setColor(Paint newColor) {
			if(newColor == null){
				paintColor = null;
				return;
			}
			else if (paintColor == null){
				paintColor = new SimpleObjectProperty<Paint>();
			}
			
			paintColor.set(newColor);	
			setStyle(createCSSStyle(this));
		}

		//Returns the colour of the fill
		@Override
	    @XmlJavaTypeAdapter(ColorAdapter.class)
		@XmlAttribute
		public Paint getFill() {
			return (paintFill == null) ? null : paintFill.get();
		}
		public ObjectProperty<Paint> paintFillProperty() {
			return this.paintFill;	
		}
		
		//Sets colour of outline if newColor is not null
		@Override
		public void setFill(Paint newColor) {
			if(newColor == null){
				paintFill = null;
				return;
			}
			else if (paintFill == null){
				paintFill = new SimpleObjectProperty<Paint>();
			}
			
			paintFill.set(newColor);
			setStyle(createCSSStyle(this));
		}


	@Override
	@XmlAttribute
    @XmlJavaTypeAdapter(StartAdapter.class)
	public Integer getStart() {
		return this.start;
	}


	@Override
	/**
	 * Sets the amount of time before text will appear
	 * @param start time
	 */
	public void setStart(Integer start) {
		if (start == null) {
			return;
		}
		this.start=start;
	}


	@Override
	@XmlAttribute
	public Integer getDuration() {
		return this.duration;
	}


	@Override
	/**
	 * Sets the amount of time before text will disappear
	 * @param duration time
	 */
	public void setDuration(Integer duration) {
		if (duration == null) {
			return;
		}
		this.duration=duration;
	}
	
	public String getStyle() {
		return (style == null) ? null : style.get();
	}
	
	//Creates a property for stroke
	public StringProperty styleProperty() {
		return this.style;
	}
	
	//Sets pixel width of border on shape if not null
	public void setStyle(String newStyle) {
		if(newStyle == null){
			style = null;
			return;
		}
		else if (style == null){
			style = new SimpleStringProperty();
		}
		
		style.set(newStyle);
	}
	
	public String createCSSStyle(Text text) {
		String tempString = null;
		
		boolean isBold = text.getBold() == null ? DEFAULT_Bold : text.getBold();
		if (isBold == true) {
			tempString = "-fx-font-weight: bold; ";
		}
		else
		{
			tempString = "-fx-font-weight: normal; ";
		}
		
		boolean isItalic = text.getItalic() == null ? DEFAULT_Italic : text.getItalic();
		if (isItalic == true) {
			tempString = tempString + "-fx-font-style: italic; ";
		}
		else
		{
			tempString = tempString + "-fx-font-style: normal; ";
		}
		
		if (text.getTextsize() == null) {
			tempString = tempString + "-fx-font-size: " + DEFAULT_Textsize + "px; ";
		}
		else
		{
			tempString = tempString + "-fx-font-size: " + text.getTextsize() + "px; ";
		}
		
		if (text.getFont() == null) {
			tempString = tempString + "-fx-font-family: \"" + DEFAULT_Font + "\", serif; ";
		}
		else
		{
			tempString = tempString + "-fx-font-family: \"" + text.getFont() + "\", serif; ";
		}
		
		return tempString;
		
	}
	
	public String convertToHex(Color color) {
		
		String string =  String.format( "#%02X%02X%02X",
				(int)( color.getRed()	* 255 ),
				(int)( color.getGreen() * 255 ),
				(int)( color.getBlue()	* 255 ) 
			);
		
		return string;
		
	}
	
	public String convertToGradient(Paint color) {
		Color color1 = ((LinearGradient) color).getStops().get(0).getColor();
		Color color2 = ((LinearGradient) color).getStops().get(1).getColor();
		String string = ("linear-gradient(" + convertToHex(color1) + ", " + convertToHex(color2) + ")");
		
		return string;
		
	}


}
