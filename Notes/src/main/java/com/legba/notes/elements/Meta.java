package com.legba.notes.elements;

public class Meta extends Element {

	public Meta(String key, String value) {
		super("meta");
		
		this.setKey(key);
		this.setValue(value);
		
	}

	
	public String getKey() {
		return (String)this.getAttribute("key");

	}

	public void setKey(String key) {
		this.setAttribute("key", key);
	}
	
	public String getValue() {
		return (String)this.getAttribute("value");

	}

	public void setValue(String value) {
		this.setAttribute("value", value);
	}
}
