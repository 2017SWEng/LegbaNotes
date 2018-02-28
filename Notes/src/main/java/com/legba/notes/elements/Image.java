package com.legba.notes.elements;


import com.legba.notes.elements.base.*;


//Bitmap Image Element (JPEGs supported)
//Other formats not guaranteed support
public class Image extends MultiMediaElement {
		
	protected Image() {
		super();
	};
	
	public Image(String path) {
		super();

		this.setPath(path);
		
	}
	
	@Override
	protected boolean isValidPath(String path) {
		
		if (
			path.length() > 0 && 
			(path.endsWith(".jpeg") || path.endsWith(".jpg"))
		) {
			return true;
		}
		
		return false;
	}
}
