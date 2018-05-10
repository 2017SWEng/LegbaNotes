package com.legba.notes.elements.base;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import javafx.scene.paint.Color;

public class ColorAdapter extends XmlAdapter<String, Color[]> {

    public Color[] unmarshal(String v) throws Exception {
    	
    	Color[] array;
    	
    	//If colour is in gradient format then takes both colours and puts them in the array
    	if (v.startsWith("gradient") ) {
    		
    		Color color1 = Color.web(v.substring(9, 16));
    		Color color2 = Color.web(v.substring(17, 24));

    		array = new Color[] {color1, color2};
    		
    		return array;
    	}
    	else
    	//else returns the one colour as an array with one element
    	{
    		array = new Color[] {Color.web(v)};
    		return array;
    	}
    	
    }

	@Override
	public String marshal(Color[] v) throws Exception {
		if (v == null) {
			return null;
		}
		else if(v.length == 2) {
			String tempString = ("gradient(" + convertToHex(v[0]) + "," + convertToHex(v[1]) + ")");
			return tempString;
		}
		else {
			return convertToHex(v[0]);
		}
	}
	
	public String convertToHex(Color color) {
		
		return String.format( "#%02X%02X%02X",
				(int)( color.getRed()	* 255 ),
				(int)( color.getGreen() * 255 ),
				(int)( color.getBlue()	* 255 ) 
			);
	}
}