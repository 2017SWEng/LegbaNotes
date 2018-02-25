package com.legba.notes.elements;

import javax.xml.bind.annotation.XmlAttribute;

import com.legba.notes.elements.base.*;

import javafx.scene.Node;

//Bitmap Image Element (JPEGs supported)
//Other formats not guaranteed support
public class Image extends SlideElement implements Pathable,Positionable,Renderable{


	private Float x;
	
	private Float y;
	
	private Float x2;
	
	private Float y2;
	
	private String path;
	
	
	public Image(String path) {
		super();

		this.setPath(path);
		
	}
	
	@Override
	@XmlAttribute
	public String getPath() {
		return this.path;

	}
	
	@Override
	public void setPath(String path) {
		if (this.isValidPath(path)) {
			this.path = path;
		}
	}

	@Override
	@XmlAttribute
	public Float getX() {
		return this.x;
	}
	

	@Override
	public void setX(Float x) {
		this.x = x;				
	}
	
	
	@Override
	@XmlAttribute
	public Float getY() {
		return this.y;
	}
	

	@Override
	public void setY(Float y) {
		this.y=y;				
	}
	

	@Override
	@XmlAttribute
	public Float getX2() {
		return this.x2;
	}
	

	@Override
	public void setX2(Float x2) {
		this.x2=x2;				
	}
	
	
	@Override
	@XmlAttribute
	public Float getY2() {
		return this.y2;
	}

	
	@Override
	public void setY2(Float y2) {
		this.y2=y2;
	}
	

	@Override
	@XmlAttribute
	public Float getWidth() {
		return this.x2 - this.x;
	};
	
	
	@Override
	@XmlAttribute
	public Float getHeight() {
		return this.y2 - this.y;
	};
	
	
	protected boolean isValidPath(String path) {
		
		if (
			path.length() > 0 && 
			(path.endsWith(".jpeg") || path.endsWith(".jpg"))
		) {
			return true;
		}
		
		return false;
	}

	@Override
	public Node render() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
