package com.legba.notes.elements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.legba.notes.elements.base.*;

import javafx.scene.paint.Color;

//Rich text element
public class Text extends SlideElement implements Transitionable,Colorable,Formatable{
	
	private Integer start;
	
	private Integer duration;
	
	private Color color;
	
	private Color fill;
	
	private String font;
	
	private Boolean italic;
	
	private Boolean bold;
	
	private Boolean underline;
	
	private Integer textsize;
	
	public Text() {
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
		return this.textsize;
	}


	@Override
	public void setTextsize(Integer size) {
		this.textsize=size;		
	}
	
	
	
	@Override
	@XmlAttribute
    @XmlJavaTypeAdapter(ColorAdapter.class)
	public Color getColor() {
		return this.color;
	}


	@Override
	public void setColor(Color col) {
		this.color=col;
	}


	@Override
	@XmlAttribute
    @XmlJavaTypeAdapter(ColorAdapter.class)
	public Color getFill() {
		return this.fill;
	}


	@Override
	public void setFill(Color fill) {
		this.color=fill;
	}

	@Override
	@XmlAttribute
    @XmlJavaTypeAdapter(StartAdapter.class)
	public Integer getStart() {
		return this.start;
	}


	@Override
	public void setStart(Integer start) {
		this.start=start;
	}


	@Override
	@XmlAttribute
	public Integer getDuration() {
		return this.duration;
	}


	@Override
	public void setDuration(Integer duration) {
		this.duration=duration;
	}


}
