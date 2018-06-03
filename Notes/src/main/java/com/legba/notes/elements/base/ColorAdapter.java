package com.legba.notes.elements.base;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;

public class ColorAdapter extends XmlAdapter<String, Paint> {

    public Paint unmarshal(String v) throws Exception {
    	
    	//If colour is in gradient format then takes both colours and puts them in the array
    	if (v.startsWith("gradient") ) {
    		
    		Color color1 = Color.web(v.substring(9, 16));
    		Color color2 = Color.web(v.substring(17, 24));
    		
    		Stop[] stops = new Stop[] {new Stop(0, color1), new Stop(1, color2)};
    		LinearGradient lg = new LinearGradient(0, 0, 1, 1, false, CycleMethod.NO_CYCLE, stops);
    		
    		return lg;
    	}
    	else
    	//else returns the one colour as an array with one element
    	{
    		return Color.web(v);
    	}
    	
    }

	@Override
	public String marshal(Paint v) throws Exception {
		if (v == null) {
			return null;
		}
		else if(v instanceof LinearGradient) {
			Color color1 = ((LinearGradient) v).getStops().get(0).getColor();
			Color color2 = ((LinearGradient) v).getStops().get(1).getColor();
			String tempString = ("gradient(" + convertToHex(color1) + "," + convertToHex(color2) + ")");
			return tempString;
		}
		else {
			return convertToHex((Color) v);
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