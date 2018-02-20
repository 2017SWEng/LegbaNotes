package com.legba.notes.elements;


// Timing controls for automatic animation/transitions of elements
public class Transition extends AttributeGroup {
	
	public Transition(Element element) {
		super(element);
		
		this.setStart(-1);
		this.setDuration(30);
	}
	
	// How long in seconds before the element appears,
	// or whether it should wait to be user-triggered
	//   - Negative is wait until trigger
	//   - Zero is immediate
	//   - Positive is the number of seconds since the 
	public int getStart() {
		return (int)this.element.getAttribute("start");
	}
	
	public void setStart(int start) {
		this.element.setAttribute("start", start);
	}
	
	
	//Duration in seconds
	public int getDuration() {
		return (int)this.element.getAttribute("start");
	}
	
	public void setDuration(int duration) {
		if (duration < -1) {
			duration = -1;
		}
		
		this.element.setAttribute("duration", duration);
		
	}	

}
