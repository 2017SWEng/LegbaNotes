package com.legba.notes.elements.base;

public abstract class PathablePositionableElement extends PositionableElement implements Pathable {

	private String path;
	
	public PathablePositionableElement(String tagName) {
		super(tagName);
		// TODO Auto-generated constructor stub
	}

	public String getPath() {
		return this.path;

	}

	public void setPath(String path) {
		if (this.isValidPath(path)) {
			this.path = path;
		}
	}
	
	protected abstract boolean isValidPath(String path);
}
