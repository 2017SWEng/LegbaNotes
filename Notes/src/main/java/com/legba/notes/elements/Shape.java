package com.legba.notes.elements;

public class Shape extends Element {

	public Position position;
	public Colors colors;
	
	public Shape(String type) {
		super("shape");


		this.position = new Position((Element)this);
		this.colors = new Colors((Element)this);
		
		this.setType(type);
		
	}
	
	public String getType() {
		return (String)this.getAttribute("type");

	}

	public void setType(String type) {
		// Only allow valid types to be set
		if(type != "ellipse" && type != "rectangle" && type != "line") {
			return;
		}
		this.setAttribute("type", type);
	}
	
	public int getStroke() {
		return (int)this.getAttribute("stroke");

	}

	public void setStroke(String stroke) {
		this.setAttribute("stroke", stroke);
	}

}
