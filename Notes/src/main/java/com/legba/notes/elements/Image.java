package com.legba.notes.elements;


import com.legba.notes.elements.base.*;



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
		// all formats listed are supported according to javafx.scene.image documentation
		if (
			path.length() > 0 && 
			(path.endsWith(".jpeg")|| 
			 path.endsWith(".JPEG")||
			 path.endsWith(".jpg") ||
			 path.endsWith(".JPG") ||
			 path.endsWith(".gif") ||
			 path.endsWith(".GIF") ||
			 path.endsWith(".png") ||
			 path.endsWith(".PNG") ||
			 path.endsWith(".bmp") ||
			 path.endsWith(".BMP")
			)
		) {
			return true;
		}
		
		return false;
	}
}
