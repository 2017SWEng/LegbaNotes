package com.legba.notes.elements;

public class Format extends Element {


	public Formats format;
	public Colors colors;
	
	public Format() {
		super("format");

		this.format = new Formats((Element)this);
		this.colors = new Colors((Element)this);
	}


}
