package com.legba.notes.componets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.scene.Node;

public abstract class Component {
	

	private String tagName;
	
	private HashMap<String, String> attributes;
	
	private ArrayList<Component> children;
	
	private String innerText;
	
	
	public Component (String tagName) {
		this.setTagName(tagName);
	}
	
	
	public abstract Node render();
	
	protected void setAttributes (HashMap<String, String> attributes) {
		if (attributes != null) {
			this.attributes = attributes;
		}
	}
	
	protected void addAttribute (String key, String value) {
		if (key != null && value != null && key.length() > 0 && value.length() > 0 ) {
			if (this.attributes == null) {
				this.attributes = new HashMap<String, String>();
			} 
			this.attributes.put(key, value);
		}
	}
	
	protected HashMap<String, String> getAttributes () {
		// Clone is used to return a copy of the hash map.
		return (HashMap<String, String>)this.attributes.clone();
	}
	
	
	protected void setChildren (Component[] children) {
		if (children != null) {
			if (this.children == null) {
				this.children = new ArrayList<Component>(Arrays.asList(children));
			}
			else {
				this.children.clear();
				this.children.addAll(Arrays.asList(children));
			}
		}
	}
	
	protected Component[] getChildren () {
		return (Component[])this.children.toArray();
	}
	
	protected String getInnerText() {
		return innerText;
	}

	protected void setInnerText(String innerText) {
		if (innerText != null) {
			this.innerText = innerText;
		}
	}


	public String getTagName() {
		return tagName;
	}


	private void setTagName(String tagName) {
		this.tagName = tagName;
	}
}
