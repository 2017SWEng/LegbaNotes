package com.legba.notes.elements.base;

import javax.xml.bind.annotation.XmlAttribute;

public abstract class SlideElement extends Element implements Positionable{

	private Float x;
	
	private Float y;
	
	private Float x2;
	
	private Float y2;
	
	public SlideElement() {
		super();
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
		if (this.x2 == null || this.x == null) {
			return null;
		}
		return this.x2 - this.x;
	};
	
	
	@Override
	@XmlAttribute
	public Float getHeight() {
		if (this.y2 == null || this.y == null) {
			return null;
		}
		return this.y2 - this.y;
	};
}
