package com.legba.notes.elements;

public class Audio extends Element {


	public Position position;
	
	public Audio(String path) {
		super("audio");


		this.position = new Position((Element)this);
		
		this.setPath(path);
		
	}
	
	public String getPath() {
		return (String)this.getAttribute("path");

	}

	public void setPath(String path) {
		//TODO:: check if this is a path before setting it
		this.setAttribute("path", path);
	}


}
