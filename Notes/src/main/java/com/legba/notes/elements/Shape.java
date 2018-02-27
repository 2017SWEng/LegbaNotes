package com.legba.notes.elements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.legba.notes.elements.base.*;

import javafx.scene.paint.Color;

public class Shape extends SlideElement implements Colorable,Positionable{
	
	private String type;
	
	private Integer stroke;
	
	private Color color;
	
	private Color fill;
	
	protected Shape() {
		
	}
	
	//Constructor with parameters position, shape and type
	public Shape(String type) {
		super();

		//Required by Element 'Shape'
		this.setType(type);
		
	}
	
	//Returns type of shape
	@XmlAttribute
	public String getType() {
		return this.type;

	}
	
	//Sets type of shape
	public void setType(String type) {
		
		// Only allow valid types to be set
		if(type.equals("ellipse") || type.equals("rectangle") || type.equals("line")) {
			this.type=type;
		}

	}
	
	//Returns pixel width of border on shape
	@XmlAttribute
	public Integer getStroke() {
		return this.stroke;

	}
	
	//Sets pixel width of border on shape
	public void setStroke(Integer stroke) {
		this.stroke=stroke;
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
