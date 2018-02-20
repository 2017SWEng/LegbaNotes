package com.legba.notes.elements;

public class Shape extends Element {

	public Position position;
	public Colors colors;
	
	//Constructor with parameters position, shape and type
	public Shape(String type) {
		super("shape");

		//Optional for Element 'Shape'
		this.position = new Position((Element)this);
		this.colors = new Colors((Element)this);
		
		//Required by Element 'Shape'
		this.setType(type);
		
	}
	
	//Returns type of shape
	public String getType() {
		return (String)this.getAttribute("type");

	}
	
	//Sets type of shape
	public void setType(String type) {
		// Only allow valid types to be set
		if(type != "ellipse" && type != "rectangle" && type != "line") {
			return;
		}
		this.setAttribute("type", type);
	}
	
	//Returns pixel width of border on shape
	public int getStroke() {
		return (int)this.getAttribute("stroke");

	}
	
	//Sets pixel width of border on shape
	public void setStroke(String stroke) {
		this.setAttribute("stroke", stroke);
	}

}
