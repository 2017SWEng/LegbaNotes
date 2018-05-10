package com.legba.notes.elements;

import com.legba.notes.elements.base.*;

public class Video extends MultiMediaElement{
	
	protected Video() {
		super();
	};
	
	public Video(String path) {
		super();

		this.setPath(path);
	}
	
	public Video(String path, float X, float Y, float X2, float Y2) {
		super();

		this.setPath(path);
		this.setX(X);
		this.setX2(X2);
		this.setY(Y);
		this.setY2(Y2);
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

}
