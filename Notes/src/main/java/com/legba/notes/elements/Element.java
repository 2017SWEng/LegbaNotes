package com.legba.notes.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//TODO:: make this serializable
public abstract class Element {
	

	private String tagName;
	
	private HashMap<String, Object> attributes;
	
	private ArrayList<Element> children;
	
	private String innerText;
	
	
	public Element (String tagName) {
		this.setTagName(tagName);
	}
	
	
	protected void setAttributes (HashMap<String, Object> attributes) {
		if (attributes != null) {
			this.attributes = attributes;
		}
	}
	
	protected void setAttribute (String key, Object value) {
		if (key != null && value != null && key.length() > 0 ) {
			if (this.attributes == null) {
				this.attributes = new HashMap<String, Object>();
			} 
			
			// Put overrides any existing value, so we don't need to check to see if the key exists already
			this.attributes.put(key, value);
		}
	}
	
	protected Object getAttribute (String key) {
		if (key != null && key.length() > 0 && this.attributes.containsKey(key)) {
			return this.attributes.get(key);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected HashMap<String, Object> getAttributes () {
		// Clone is used to return a copy of the hash map.
		return (HashMap<String, Object>)this.attributes.clone();
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
	
	protected String getInnerText() {
		return innerText;
	}

	protected void setInnerText(String innerText) {
		if (innerText != null) {
			this.innerText = innerText;
		}
	}


	protected String getTagName() {
		return tagName;
	}


	protected void setTagName(String tagName) {
		this.tagName = tagName;
	}
}
