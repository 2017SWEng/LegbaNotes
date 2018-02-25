package com.legba.notes.elements.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//TODO:: make this serializable
public abstract class Element {
	

	private String tagName;
		
	private ArrayList<Element> children;
		
	
	public Element (String tagName) {
		this.setTagName(tagName);
	}
	
	protected void setChildren (Element[] children) {
		if (children != null) {
			if (this.children == null) {
				this.children = new ArrayList<Element>(Arrays.asList(children));
			}
			else {
				this.children.clear();
				this.children.addAll(Arrays.asList(children));
			}
		}
	}
	
	protected Element[] getChildren () {
		return (Element[])this.children.toArray();
	}
	
	protected String getTagName() {
		return tagName;
	}


	protected void setTagName(String tagName) {
		this.tagName = tagName;
	}
}
