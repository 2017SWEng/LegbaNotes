package com.legba.notes.elements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.legba.notes.elements.base.*;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Shape extends SlideElement implements Colorable,Positionable,Renderable{
	
	private String type;
	
	private Integer stroke;
	
	private Color color;
	
	private Color fill;
	
	private Float x;
	
	private Float y;
	
	private Float x2;
	
	private Float y2;
	
	protected Shape() {
		
	}
	
	//Constructor with parameters position, shape and type
	public Shape(String type) {
		super();

		//Required by Element 'Shape'
		this.setType(type);
		
	}
	
	//Returns type of shape
	@XmlAttribute(required=true)
	public String getType() {
		return this.type;

	}
	
	//Sets type of shape
	public void setType(String type) {
		// Only allow valid types to be set
		if(type != "ellipse" && type != "rectangle" && type != "line") {
			return;
		}
		this.type=type;
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
	
	@Override
	@XmlAttribute
	public Float getX() {
		return this.x;
	}
	

	@Override
	public void setX(Float x) {
		this.x = x;				
	}
	
	
	@Override
	@XmlAttribute
	public Float getY() {
		return this.y;
	}
	

	@Override
	public void setY(Float y) {
		this.y=y;				
	}
	

	@Override
	@XmlAttribute
	public Float getX2() {
		return this.x2;
	}
	

	@Override
	public void setX2(Float x2) {
		this.x2=x2;				
	}
	
	
	@Override
	@XmlAttribute
	public Float getY2() {
		return this.y2;
	}

	
	@Override
	public void setY2(Float y2) {
		this.y2=y2;
	}
	

	@Override
	@XmlAttribute
	public Float getWidth() {
		return this.x2 - this.x;
	};
	
	
	@Override
	@XmlAttribute
	public Float getHeight() {
		return this.y2 - this.y;
	};

	@Override
	public Node render() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
