package com.legba.notes.elements.base;

public abstract class TransitionableColorableFormatableElement extends ColorableFormatableElement implements Transitionable{

	private int start;
	private int duration;
	
	public TransitionableColorableFormatableElement(String tagName) {
		super(tagName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getStart() {
		return this.start;
	}


	@Override
	public void setStart(int start) {
		this.start=start;
	}


	@Override
	public int getDuration() {
		return this.duration;
	}


	@Override
	public void setDuration(int duration) {
		this.duration=duration;
	}

}
