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
