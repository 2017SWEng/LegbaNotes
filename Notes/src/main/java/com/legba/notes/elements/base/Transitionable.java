package com.legba.notes.elements.base;


// Timing controls for automatic animation/transitions of elements
public interface Transitionable{
	

	// How long in seconds before the element appears,
	// or whether it should wait to be user-triggered
	//   - Negative is wait until trigger
	//   - Zero is immediate
	//   - Positive is the number of seconds since the 
	public int getStart();
	
	public void setStart(int start);
	
	
	//Duration in seconds
	public int getDuration();
	
	public void setDuration(int duration);	

}
