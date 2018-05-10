package com.legba.notes.elements.base;

import javafx.scene.paint.Color;

// Defines the fill color and the foreground/stroke/text color
public interface Colorable {

	// The foreground/stroke/text colour.
	public Color[] getColor();

	public void setColor(Color[] col);

	
	// The background colour.
	public Color[] getFill();

	public void setFill(Color[] fill);

	
}
