package com.legba.notes.elements;

import com.legba.notes.elements.base.*;

import javafx.scene.Node;

//Bitmap Image Element (JPEGs supported)
//Other formats not guaranteed support
public class Image extends PathablePositionableElement implements Renderable{

	public Image(String path) {
		super("image");

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

	@Override
	public Node render() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
