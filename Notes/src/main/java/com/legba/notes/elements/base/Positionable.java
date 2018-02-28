package com.legba.notes.elements.base;


// The position and size (offset) of an element
public interface Positionable {
	
	
	// The x co-ordinate of the top left corner of the element
	public Float getX();

	public void setX(Float x);
	
	
	// The y co-ordinate of the top left corner of the element
	public Float getY();

	public void setY(Float y);

	
	// The x co-ordinate of the bottom right corner of the element
	public Float getX2();

	public void setX2(Float x2);
	
	
	// The y co-ordinate of the bottom right corner of the element
	public Float getY2();

	public void setY2(Float y2);
	
	
	// The width and height of an element is defined as the difference
	// between the (x, y) and (x2, y2) coordinates.
	public Float getWidth();
	
	public Float getHeight();
}
