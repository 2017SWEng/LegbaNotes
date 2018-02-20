package com.legba.notes.elements;

import com.legba.notes.elements.Element;

public class Presentation extends Element {

	
	public Formats format;
	public Colors colors;
	
	public Presentation() {
		super("presentation");

		this.format = new Formats((Element)this);
		this.colors = new Colors((Element)this);
	}

}
