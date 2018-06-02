package com.legba.notes.elements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.legba.notes.elements.base.*;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;


public class Shape extends SlideElement implements Colorable{
	
	private String type;
	
	private IntegerProperty stroke;
	
	private ObjectProperty<Paint> paintColor;
	
	private ObjectProperty<Paint> paintFill;
	
	protected Shape() {
		
	}
	
	//Constructor with parameter type
	public Shape(String type) {
		super();
		//Required by Element 'Shape'
		this.setType(type);
		stroke = new SimpleIntegerProperty();
		paintColor = new SimpleObjectProperty<Paint>();
		paintFill = new SimpleObjectProperty<Paint>();
	}
	
	//Returns type of shape
	@XmlAttribute
	public String getType() {
		return this.type;
	}
	
	//Sets type of shape
	public void setType(String type) throws IllegalArgumentException{
		
		// Only allow valid types to be set
		if(type.equals("ellipse") || type.equals("rectangle") || type.equals("line")) {
			this.type=type;
		}
		else {
			System.err.println("Error: Invalid shape type");
			throw new IllegalArgumentException("Invalid shape type");		
			
		}
	}
	
	//Returns pixel width of border on shape
	@XmlAttribute
	public Integer getStroke() {
		return (stroke == null) ? null : stroke.get();
	}
	
	//Creates a property for stroke
	public IntegerProperty strokeProperty() {
		return this.stroke;
	}
	
	//Sets pixel width of border on shape if not null
	public void setStroke(Integer newStroke) {
		if(newStroke == null){
			stroke = null;
			return;
		}
		else if (stroke == null){
			stroke = new SimpleIntegerProperty();
		}
		
		stroke.set(newStroke);
	}

	//Returns the colour of the outline
	@Override
    @XmlJavaTypeAdapter(ColorAdapter.class)
	@XmlAttribute
	public Paint getColor() {
		return (paintColor == null) ? null : paintColor.get();
	}
	
	public ObjectProperty<Paint> paintColorProperty() {
		return this.paintColor;	
	}
	
	//Sets colour of outline if newColor is not null
	@Override
	public void setColor(Paint newColor) {
		if(newColor == null){
			paintColor = null;
			return;
		}
		else if (paintColor == null){
			paintColor = new SimpleObjectProperty<Paint>();
		}
		
		paintColor.set(newColor);	
	}

	//Returns the colour of the fill
	@Override
    @XmlJavaTypeAdapter(ColorAdapter.class)
	@XmlAttribute
	public Paint getFill() {
		return (paintFill == null) ? null : paintFill.get();
	}
	public ObjectProperty<Paint> paintFillProperty() {
		return this.paintFill;	
	}
	
	//Sets colour of outline if newColor is not null
	@Override
	public void setFill(Paint newColor) {
		if(newColor == null){
			paintFill = null;
			return;
		}
		else if (paintFill == null){
			paintFill = new SimpleObjectProperty<Paint>();
		}
		
		paintFill.set(newColor);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Shape [" + (getType() != null ? "getType()=" + getType() + ", " : "")
				+ (getStroke() != null ? "getStroke()=" + getStroke() + ", " : "")
				+ (getColor() != null ? "getColor()=" + getColor() + ", " : "")
				+ (getFill() != null ? "getFill()=" + getFill() + ", " : "")
				+ (getX() != null ? "getX()=" + getX() + ", " : "") + (getY() != null ? "getY()=" + getY() + ", " : "")
				+ (getX2() != null ? "getX2()=" + getX2() + ", " : "") + (getY2() != null ? "getY2()=" + getY2() : "")
				+ "]";
	}
	
}
