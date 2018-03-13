package com.legba.notes.elements.base;

import javax.xml.bind.annotation.XmlAttribute;


import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;


public abstract class SlideElement extends Element implements Positionable{

	private FloatProperty x;
	
	private FloatProperty y;
	
	private FloatProperty x2;
	
	private FloatProperty y2;
	
	public SlideElement() {
		super();
		
		x = new SimpleFloatProperty();
		x2 = new SimpleFloatProperty();
		y = new SimpleFloatProperty();
		y2 = new SimpleFloatProperty();
	}
	
	//Getter for x
	@Override
	@XmlAttribute
	public final Float getX() {
		return x.get();
	}
	
	//Creates a property for x
	public FloatProperty xProperty() {
		return this.x;
	}
	
	//Setter for x
	@Override
	public void setX(Float newX) {
		x.set(newX);				
	}
	
	//Getter for y
	@Override
	@XmlAttribute
	public final Float getY() {
		return y.get();
	}
	
	//Creates a property for y
	public FloatProperty yProperty() {
		return this.y;
	}
	
	//Setter for y
	@Override
	public void setY(Float newY) {
		y.set(newY);				
	}
	
	//Getter for x2
	@Override
	@XmlAttribute
	public final Float getX2() {
		return x2.get();
	}
	
	//Creates a property for x2
	public FloatProperty x2Property() {
		return this.x2;
	}
	
	//Setter for x2
	@Override
	public void setX2(Float newX2) {
		x2.set(newX2);				
	}
	
	//Getter for y2
	@Override
	@XmlAttribute
	public final Float getY2() {
		return y2.get();
	}
	
	//Creates a property for y2
	public FloatProperty y2Property() {
		return this.y2;
	}
	
	//Setter for y2
	@Override
	public void setY2(Float newY2) {
		y2.set(newY2);				
	}
	
	//Calculates and returns width
	@Override
	public Float getWidth() {
		if (this.x2 == null || this.x == null) {
			return null;
		}
		return this.getX2() - this.getX();
	};
	
	//Calculates and returns height
	@Override
	public Float getHeight() {
		if (this.y2 == null || this.y == null) {
			return null;
		}
		return this.getY2() - this.getY();
	};
}
