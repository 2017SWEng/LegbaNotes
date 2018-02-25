package com.legba.notes.elements;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.legba.notes.elements.base.*;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Slide extends Element implements Transitionable,Colorable,Formatable,Renderable{
	
	private Color color;
	
	private Color fill;
	
	private String font;
	
	private Boolean italic;
	
	private Boolean bold;
	
	private Boolean underlined;
	
	private Integer size;
	
	private Integer start;
	
	private Integer duration;
	
	List<SlideElement> slideElements;
	
	public Slide() {
		super();
		
		this.slideElements = new ArrayList<SlideElement>();
	}
	
	
	public void addSlideElement(SlideElement slideElements) {
		this.slideElements.add(slideElements);
	}
	
	public SlideElement getSlideElement(int index) {
		if(index < this.slideElements.size()) {
			return this.slideElements.get(index);
		}
		return null;
	}
	
	
	@Override
	@XmlAttribute
	public int getStart() {
		return this.start;
	}


	@Override
	public void setStart(Integer start) {
		this.start=start;
	}


	@Override
	@XmlAttribute
	public int getDuration() {
		return this.duration;
	}


	@Override
	public void setDuration(Integer duration) {
		this.duration=duration;
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
	public Node render() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
