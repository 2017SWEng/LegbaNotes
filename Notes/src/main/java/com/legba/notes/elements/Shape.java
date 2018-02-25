package com.legba.notes.elements;

import com.legba.notes.elements.base.*;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Shape extends ColorablePositionableElement implements Renderable{
	
	private String type;
	private int stroke;
	
	//Constructor with parameters position, shape and type
	public Shape(String type) {
		super("shape");

		//Required by Element 'Shape'
		this.setType(type);
		
	}
	
	//Returns type of shape
	public String getType() {
		return this.type;

	}
	
	//Sets type of shape
	public void setType(String type) {
		// Only allow valid types to be set
		if(type != "ellipse" && type != "rectangle" && type != "line") {
			return;
		}
		this.type=type;
	}
	
	//Returns pixel width of border on shape
	public int getStroke() {
		return this.stroke;

	}
	
	//Sets pixel width of border on shape
	public void setStroke(int stroke) {
		this.stroke=stroke;
	}

	@Override
	public Node render() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
