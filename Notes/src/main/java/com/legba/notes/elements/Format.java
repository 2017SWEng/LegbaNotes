package com.legba.notes.elements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.legba.notes.elements.base.*;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Format extends Element implements Colorable,Formatable{
	
	private String font;
	
	private Boolean italic;
	
	private Boolean bold;
	
	private Boolean underlined;
	
	private Integer size;
	
	private Color color;
	
	private Color fill;
	
	public Format() {
		super();

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
	public Boolean getUnderlined() {
		return this.underlined;
	}


	@Override
	public void setUnderlined(Boolean underlined) {
		this.underlined=underlined;		
	}


	@Override
	@XmlAttribute
	public Integer getSize() {
		return this.size;
	}


	@Override
	public void setSize(Integer size) {
		this.size=size;		
	}
	
	@Override
    @XmlJavaTypeAdapter(ColorAdapter.class)
	@XmlAttribute
	public Color getColor() {
		return this.color;
	}


	@Override
	public void setColor(Color col) {
		this.color=col;
	}


	@Override
    @XmlJavaTypeAdapter(ColorAdapter.class)
	@XmlAttribute
	public Color getFill() {
		return this.fill;
	}


	@Override
	public void setFill(Color fill) {
		this.color=fill;
	}

}
