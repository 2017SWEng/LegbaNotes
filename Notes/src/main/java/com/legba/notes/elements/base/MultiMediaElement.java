package com.legba.notes.elements.base;

import javax.xml.bind.annotation.XmlAttribute;

public abstract class MultiMediaElement extends SlideElement implements Pathable{

	private String path;
	
	public MultiMediaElement() {
		super();
	}
	
	@Override
	@XmlAttribute
	public String getPath() {
		return this.path;

	}
	
	@Override
	public void setPath(String path) {
		if (this.isValidPath(path)) {
			this.path = path;
		}
	}

	protected abstract boolean isValidPath(String path);
}
