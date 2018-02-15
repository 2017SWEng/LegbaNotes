package com.legba.notes.componets;

// The position and size (offset) of an element
public interface Positionable {
	
	// The x co-ordinate of the top left corner of the element
	public float getX();
	public void setX(float x);
	
	// The y co-ordinate of the top left corner of the element
	public float getY();
	public void setY(float y);

	// The x co-ordinate of the bottom right corner of the element
	public float getX2();
	public void setX2(float x2);
	
	// The y co-ordinate of the bottom right corner of the element
	public float getY2();
	public void setY2(float y2);
	
	// The width and height of an element is defined as the difference
	// between the (x, y) and (x2, y2) coordinates.
	public float getWidth();
	public float getHeight();
}
