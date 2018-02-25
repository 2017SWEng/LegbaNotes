package com.legba.notes.elements.base;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import javafx.scene.paint.Color;

public class ColorAdapter extends XmlAdapter<String, Color> {

    public Color unmarshal(String v) throws Exception {
        return Color.web(v);
    }

    public String marshal(Color v) throws Exception {
    	
    	if (v == null) {
    		return null;
    	}
    	
        return String.format( "#%02X%02X%02X",
    				(int)( v.getRed()	* 255 ),
    				(int)( v.getGreen() * 255 ),
    				(int)( v.getBlue()	* 255 ) 
				);
    }
}