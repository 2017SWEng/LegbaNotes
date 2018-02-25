package com.legba.notes.elements;

import javax.xml.bind.annotation.XmlAttribute;

import com.legba.notes.elements.base.Element;

public class Meta extends Element {

	private String key;
	
	private String value;
	
	public Meta(String key, String value) {
		super();
		
		this.setKey(key);
		this.setValue(value);
		
	}

	@XmlAttribute(required=true)
	public String getKey() {
		return (String)this.key;

	}

	public void setKey(String key) {
		this.key=key;
	}
	
	@XmlAttribute(required=true)
	public String getValue() {
		return this.value;

	}

	public void setValue(String value) {
		this.value=value;
	}
}
