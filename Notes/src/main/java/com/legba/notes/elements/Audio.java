package com.legba.notes.elements;


import com.legba.notes.elements.base.*;

public class Audio extends MultiMediaElement{

	protected Audio() {
		super();
	}
	
	public Audio(String path) {
		super();

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

}
