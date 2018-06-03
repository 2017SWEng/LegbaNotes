package com.legba.notes.elements.base;

import javafx.scene.paint.Paint;

// Defines the fill color and the foreground/stroke/text color
public interface Colorable {

	// The foreground/stroke/text colour.
	public Paint getColor();

	public void setColor(Paint col);

	
	// The background colour.
	public Paint getFill();

	public void setFill(Paint fill);

	
}
