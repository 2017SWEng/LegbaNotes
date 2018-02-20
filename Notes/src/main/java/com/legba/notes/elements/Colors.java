package com.legba.notes.elements;

import javafx.scene.paint.Color;

// Defines the fill color and the foreground/stroke/text color
public class Colors extends AttributeGroup {
	
	public Colors(Element element) {
		super(element);
		
		this.setColor(Color.BLACK);
		this.setFill(Color.ANTIQUEWHITE);
	}
	
	// The foreground/stroke/text colour.
	public Color[] getColor() {
		return (Color[])this.element.getAttribute("color");
	}

	public void setColor(Color col) {
		this.element.setAttribute("color", new Color[] {col});				
	}

	public void setColor(Color col1, Color col2) {
		this.element.setAttribute("color", new Color[] {col1,col2});		
	}

	// The background colour.
	public Color[] getFill() {
		return (Color[])this.element.getAttribute("fill");
	}

	public void setFill(Color fill) {
		this.element.setAttribute("fill", new Color[] {fill});				
	}

	public void setFill(Color fill1, Color fill2) {
		this.element.setAttribute("fill", new Color[] {fill1,fill2});		
	}

}
