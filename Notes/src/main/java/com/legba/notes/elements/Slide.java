package com.legba.notes.elements;

public class Slide extends Element{
	
	public Formats format;
	public Colors colors;
	public Transition transition;
	
	public Slide() {
		super("slide");
		
		this.format = new Formats((Element)this);
		this.colors = new Colors((Element)this);
		this.transition = new Transition((Element)this);
	}

	
}
