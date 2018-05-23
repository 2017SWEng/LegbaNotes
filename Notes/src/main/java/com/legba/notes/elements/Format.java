package com.legba.notes.elements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.legba.notes.elements.base.*;

import javafx.scene.paint.Color;
/**
 * Class is the format element and allows the formatting 
 * of the format elements parameters such as setting bold, italic etc...
 * Class extends Element and implements Colorable and Formatable.
 * If any parameter is set to null its state will not change.
 * @author vc622
 */
@XmlRootElement(name="Format")
public class Format extends Element implements Colorable,Formatable{
	
	private String font;
	
	private Boolean italic;
	
	private Boolean bold;
	
	private Boolean underline;
	
	private Integer size;
	
	private Color color;
	
	private Color fill;
	
	private String text;
	
	public Format() {
		super();
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
	}


	@Override
	@XmlAttribute
	public Boolean getUnderline() {
		return this.underline;
	}


	@Override
	/**
	 * Underlines Format element
	 * @param underline
	 */
	public void setUnderline(Boolean underline) {
		if (underline == null) {
			return;
		}
		this.underline=underline;		
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
	}
	
	@Override
    @XmlJavaTypeAdapter(ColorAdapter.class)
	@XmlAttribute
	public Color getColor() {
		return this.color;
	}

	/**
	 * Sets color of Format element
	 * @param color
	 */
	@Override
	public void setColor(Color col) {
		if (col == null) {
			return;
		}
		this.color=col;
	}


	@Override
    @XmlJavaTypeAdapter(ColorAdapter.class)
	@XmlAttribute
	public Color getFill() {
		return this.fill;
	}


	@Override
	/**
	 * Sets color of fill for Format element
	 * @param fill
	 */
	public void setFill(Color fill) {
		if (fill == null) {
			return;
		}
		this.fill=fill;
	}

}
