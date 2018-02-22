package com.legba.notes.elements;

import com.legba.notes.elements.Element;
//Rich text element
public class Text extends Element {

	public Formats format;
	public Colors colors;
	public Transition transition;
	public Position position;
	
	//Constructor with parameters format, color, transition and position
	public Text() {
		super("text");
		
		//Optional for text element
		this.format = new Formats((Element)this);
		this.colors = new Colors((Element)this);
		this.transition = new Transition((Element)this);
		this.position = new Position((Element)this);
	}

}
