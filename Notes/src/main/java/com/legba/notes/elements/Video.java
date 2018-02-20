package com.legba.notes.elements;

public class Video extends Element {


	public Position position;
	
	public Video(String path) {
		super("video");


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
