package com.legba.notes.elements.base;

public abstract class PositionableElement extends Element implements Positionable{

	private float x;
	private float y;
	private float x2;
	private float y2;
	
	public PositionableElement(String tagName) {
		super(tagName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getX() {
		return this.x;
	}
	

	@Override
	public void setX(float x) {
		this.x = x;				
	}
	
	
	@Override
	public float getY() {
		return this.y;
	}
	

	@Override
	public void setY(float y) {
		this.y=y;				
	}
	

	@Override
	public float getX2() {
		return this.x2;
	}
	

	@Override
	public void setX2(float x2) {
		this.x2=x2;				
	}
	
	
	@Override
	public float getY2() {
		return this.y2;
	}

	
	@Override
	public void setY2(float y2) {
		this.y2=y2;
	}
	

	@Override
	public float getWidth() {
		return this.x2 - this.x;
	};
	
	
	@Override
	public float getHeight() {
		return this.y2 - this.y;
	};

}
