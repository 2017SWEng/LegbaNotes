package com.legba.notes.elements;

import com.legba.notes.elements.base.*;

import javafx.scene.Node;

public class Audio extends PathablePositionableElement implements Renderable{


	public Audio(String path) {
		super("audio");

		this.setPath(path);
		
	}
	
	@Override
	protected boolean isValidPath(String path) {
		
		if (
			path.length() > 0 && 
			path.endsWith(".wav")
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
