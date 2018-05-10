package com.legba.notes.elements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.legba.notes.elements.base.*;

import javafx.scene.paint.Color;

@XmlRootElement(name="Format")
public class Format extends Element implements Colorable,Formatable{
	
	private String font;
	
	private Boolean italic;
	
	private Boolean bold;
	
	private Boolean underline;
	
	private Integer size;
	
	private Color[] color;
	
	private Color[] fill;
	
	private String text;
	
	public Format() {
		super();

	}
	
	@XmlValue
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text=text;		
	}
	
	@Override
	@XmlAttribute
	public String getFont() {
		return this.font;
	}


	@Override
	public void setFont(String font) {
		//TODO:: Check to make sure font is valid
		this.font=font;		
	}


	@Override
	@XmlAttribute
	public Boolean getItalic() {
		return this.italic;
	}


	@Override
	public void setItalic(Boolean italic) {
		this.italic=italic;
	}


	@Override
	@XmlAttribute
	public Boolean getBold() {
		return this.bold;
	}


	@Override
	public void setBold(Boolean bold) {
		this.bold=bold;
	}


	@Override
	@XmlAttribute
	public Boolean getUnderline() {
		return this.underline;
	}


	@Override
	public void setUnderline(Boolean underline) {
		this.underline=underline;		
	}


	@Override
	@XmlAttribute
	public Integer getTextsize() {
		return this.size;
	}


	@Override
	public void setTextsize(Integer size) {
		this.size=size;		
	}
	
	@Override
    @XmlJavaTypeAdapter(ColorAdapter.class)
	@XmlAttribute
	public Color[] getColor() {
		return this.color;
	}


	@Override
	public void setColor(Color[] col) {
		this.color=col;
	}


	@Override
    @XmlJavaTypeAdapter(ColorAdapter.class)
	@XmlAttribute
	public Color[] getFill() {
		return this.fill;
	}


	@Override
	public void setFill(Color[] fill) {
		this.fill=fill;
	}

}
