package com.legba.notes.componets;

import javafx.scene.paint.Color;

// Defines the fill color and the foreground/stroke/text color
public interface Colorable {

	// The foreground/stroke/text colour.
	public Color[] getColor();
	public void setColor(Color col);
	public void setColor(Color col1, Color col2);
	
	// The background colour.
	public Color[] getFill();
	public void setFill(Color col);
	public void setFill(Color col1, Color col2);
}
