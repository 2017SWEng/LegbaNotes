package com.legba.notes.elements;

//Bitmap Image Element (JPEGs supported)
//Other formats not guaranteed support
public class Image extends Element {

	//Image also has a position
	public Position position;
	
	public Image(String path) {
		super("image");


		this.position = new Position((Element)this);
		
		this.setPath(path);
		
	}
	//The local filepath of the media file
	public String getPath() {
		return (String)this.getAttribute("path");

	}

	public void setPath(String path) {
		//TODO:: check if this is a path before setting it
		this.setAttribute("path", path);
	}

}
