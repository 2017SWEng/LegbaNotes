package com.legba.notes.elements;

import com.legba.notes.elements.Element;

public class Text extends Element {

	public Formats format;
	public Colors colors;
	public Transition transition;
	public Position position;
	
	public Text() {
		super("text");
		
		this.format = new Formats((Element)this);
		this.colors = new Colors((Element)this);
		this.transition = new Transition((Element)this);
		this.position = new Position((Element)this);
	}

}
