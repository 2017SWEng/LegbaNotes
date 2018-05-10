package com.legba.notes.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.legba.notes.elements.base.*;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

//Rich text element
public class Text extends SlideElement implements Transitionable,Colorable,Formatable{
	
	private Integer start;
	
	private Integer duration;
	
	private Paint color;
	
	private Paint fill;
	
	private String font;
	
	private Boolean italic;
	
	private Boolean bold;
	
	private Boolean underline;
	
	private Integer textsize;
	

	private List<Object> contents;
	
	public Text() {
		super();
		
		this.contents = new ArrayList<Object>();
	}
	@XmlElementRefs({
    	@XmlElementRef(name="Format", type=Format.class),
    	@XmlElementRef(name="Br", type=Br.class)
	})
	@XmlMixed
	public List<Object> getContents() {
		return this.contents;
	}

	public void setContents(List<Object> contents) {
		//TODO:: Check to make sure font is valid
		
		this.contents=contents;		
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
	public Paint getColor() {
		return this.color;
	}


	@Override
	public void setColor(Paint col) {
		this.color=col;
	}


	@Override
	@XmlAttribute
    @XmlJavaTypeAdapter(ColorAdapter.class)
	public Paint getFill() {
		return this.fill;
	}


	@Override
	public void setFill(Paint fill) {
		this.fill=fill;
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
