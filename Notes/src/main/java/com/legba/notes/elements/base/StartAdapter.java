package com.legba.notes.elements.base;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class StartAdapter extends XmlAdapter<String, Integer> {

    public Integer unmarshal(String v) throws Exception {
    	if (v == null) {
    		return null;
    	}
    	else if (v=="trigger") {
    		return -1;
    	}
    	else {
    		return new Integer(v);
    	}
    }

    public String marshal(Integer v) throws Exception {
    	
    	if (v == null) {
    		return null;
    	}
    	else if (v<0) {
    		return "trigger";
    	}
    	else {
    		return v.toString();
    	}
    }
}
