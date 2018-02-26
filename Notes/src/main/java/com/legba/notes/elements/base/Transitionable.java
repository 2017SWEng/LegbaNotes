package com.legba.notes.elements.base;


// Timing controls for automatic animation/transitions of elements
public interface Transitionable{
	

	// How long in seconds before the element appears,
	// or whether it should wait to be user-triggered
	//   - Negative is wait until trigger
	//   - Zero is immediate
	//   - Positive is the number of seconds since the 
	public Integer getStart();
	
	public void setStart(Integer start);
	
	
	//Duration in seconds
	public Integer getDuration();
	
	public void setDuration(Integer duration);	

}
