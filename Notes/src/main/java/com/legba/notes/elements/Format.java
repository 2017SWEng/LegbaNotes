package com.legba.notes.elements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.legba.notes.elements.base.*;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Paint;

/**
 * Class is the format element and allows the formatting 
 * of the format elements parameters such as setting bold, italic etc...
 * Class extends Element and implements Colorable and Formatable.
 * If any parameter is set to null its state will not change.
 * @author vc622
 */
@XmlRootElement(name="Format")
public class Format extends Element implements Colorable,Formatable{
	
	private final boolean DEFAULT_Italic = false;
	private final boolean DEFAULT_Bold = false;
	private final boolean DEFAULT_Underline = false;
	
	private final Integer DEFAULT_Textsize = 10;
	private final String DEFAULT_Font = "Times New Roman";
	
	private String font;
	
	private Boolean italic;
	private Boolean bold;

	private BooleanProperty underline;
	
	private Integer size;
	
	private ObjectProperty<Paint> paintColor;
	private ObjectProperty<Paint> paintFill;
	
	private String text;
	private StringProperty style;
	
	public Format() {
		super();
		
		setStyle(createCSSStyle(this));
	}
	
	@XmlValue
	public String getText() {
		return this.text;
	}
	
	/**
	 * Sets text of Format Element
	 * @param text
	 */
	public void setText(String text) {
		if (text == null) {
			return;
		}
		
		this.text=text;		
	}
	
	@Override
	@XmlAttribute
	public String getFont() {
		return this.font;
	}

	@Override
	/**
	 * Sets font of Format Element
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
	 * Sets format to italic
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
	 * Sets format to bold
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
	 * Underlines Format element
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
		return this.size;
	}

	@Override
	/**
	 * Sets textsize of Format element
	 * @param size
	 */
	public void setTextsize(Integer size) {
		if (size == null) {
			return;
		}
		this.size=size;		
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
	}
		
	//Creates a property for style
	public StringProperty styleProperty() {
		return this.style;
	}
		
	//Sets style if not null
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
		
	public String createCSSStyle(Format format) {
		String tempString = null;
		
		boolean isBold = format.getBold() == null ? DEFAULT_Bold : format.getBold();
		if (isBold == true) {
			tempString = "-fx-font-weight: bold; ";
		}
		else
		{
			tempString = "-fx-font-weight: normal; ";
		}
			
		boolean isItalic = format.getItalic() == null ? DEFAULT_Italic : format.getItalic();
		if (isItalic == true) {
			tempString = tempString + "-fx-font-style: italic; ";
		}
		else
		{
			tempString = tempString + "-fx-font-style: normal; ";
		}
			
		if (format.getTextsize() == null) {
			tempString = tempString + "-fx-font-size: " + DEFAULT_Textsize + "px; ";
		}
		else
		{
			tempString = tempString + "-fx-font-size: " + format.getTextsize() + "px; ";
		}
			
		if (format.getFont() == null) {
			tempString = tempString + "-fx-font-family: \"" + DEFAULT_Font + "\", serif; ";
		}
		else
		{
			tempString = tempString + "-fx-font-family: \"" + format.getFont() + "\", serif; ";
		}
			
		return tempString;			
	}
}
