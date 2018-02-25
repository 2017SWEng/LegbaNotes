package com.legba.notes.elements.base;

import javafx.scene.paint.Color;

public abstract class ColorablePositionableElement extends PositionableElement implements Colorable {

	private Color[] color;
	private Color[] fill;
	
	public ColorablePositionableElement(String tagName) {
		super(tagName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color[] getColor() {
		return this.color;
	}


	@Override
	public void setColor(Color col) {
		this.color=new Color[]{col};
	}


	@Override
	public void setColor(Color col1, Color col2) {
		this.color=new Color[]{col1,col2};
	}


	@Override
	public Color[] getFill() {
		return this.fill;
	}


	@Override
	public void setFill(Color fill) {
		this.color=new Color[]{fill};
	}


	@Override
	public void setFill(Color fill1, Color fill2) {
		this.color=new Color[]{fill1,fill2};
	}


}

