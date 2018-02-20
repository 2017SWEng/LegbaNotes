package com.legba.notes.elements;


// The position and size (offset) of an element
public class Position extends AttributeGroup {
	
	
	public Position(Element element) {
		super(element);
		
		this.setX(0);
		this.setY(0);
		this.setX2(0);
		this.setY2(0);
	}
	
	// The x co-ordinate of the top left corner of the element
	public float getX() {
		return (float)this.element.getAttribute("x");
	}

	public void setX(float x) {
		this.element.setAttribute("x", x);				
	}
	
	// The y co-ordinate of the top left corner of the element
	public float getY() {
		return (int)this.element.getAttribute("y");
	}

	public void setY(float y) {
		this.element.setAttribute("y", y);				
	}

	// The x co-ordinate of the bottom right corner of the element
	public float getX2() {
		return (float)this.element.getAttribute("x2");
	}

	public void setX2(float x2) {
		this.element.setAttribute("x2", x2);				
	}
	
	// The y co-ordinate of the bottom right corner of the element
	public float getY2() {
		return (float)this.element.getAttribute("y2");
	}

	public void setY2(float y2) {
		this.element.setAttribute("y2", y2);				
	}
	
	// The width and height of an element is defined as the difference
	// between the (x, y) and (x2, y2) coordinates.
	public float getWidth() {
		return (float)this.element.getAttribute("x2") - (float)this.element.getAttribute("x");
	};
	public float getHeight() {
		return (float)this.element.getAttribute("y2") - (float)this.element.getAttribute("y");
	};
}
