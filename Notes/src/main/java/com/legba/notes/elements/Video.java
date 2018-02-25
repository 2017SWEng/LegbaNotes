package com.legba.notes.elements;

import com.legba.notes.elements.base.*;

import javafx.scene.Node;

public class Video extends PathablePositionableElement implements Renderable{

	public Video(String path) {
		super("video");

		this.setPath(path);
		
	}
	
	@Override
	protected boolean isValidPath(String path) {
		
		if (
			path.length() > 0 && 
			(path.endsWith(".mp4") || path.endsWith(".MP4"))
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
