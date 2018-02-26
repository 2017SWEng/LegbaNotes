package com.legba.notes.elements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.legba.notes.elements.base.*;

import javafx.scene.Node;
import javafx.scene.paint.Color;

//Rich text element
public class Text extends SlideElement implements Positionable,Transitionable,Colorable,Formatable,Renderable{
	
	private Integer start;
	
	private Integer duration;
	
	private Float x;
	
	private Float y;
	
	private Float x2;
	
	private Float y2;
	
	private Color color;
	
	private Color fill;
	
	private String font;
	
	private Boolean italic;
	
	private Boolean bold;
	
	private Boolean underlined;
	
	private Integer size;
	
	public Text() {
		super();
	}
	
	@Override
	@XmlAttribute
	public String getFont() {
		return this.font;
	}

	@Override
	public void setFont(String font) {
		//TODO:: Check to make sure font is valid
		this.font=font;		
	}


	@Override
	@XmlAttribute
	public Boolean getItalic() {
		return this.italic;
	}


	@Override
	public void setItalic(Boolean italic) {
		this.italic=italic;
	}


	@Override
	@XmlAttribute
	public Boolean getBold() {
		return this.bold;
	}


	@Override
	public void setBold(Boolean bold) {
		this.bold=bold;
	}


	@Override
	@XmlAttribute
	public Boolean getUnderlined() {
		return this.underlined;
	}


	@Override
	public void setUnderlined(Boolean underlined) {
		this.underlined=underlined;		
	}


	@Override
	@XmlAttribute
	public Integer getSize() {
		return this.size;
	}


	@Override
	public void setSize(Integer size) {
		this.size=size;		
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
		if (this.x2 == null || this.x == null) {
			return null;
		}
		return this.x2 - this.x;
	};
	
	
	@Override
	@XmlAttribute
	public Float getHeight() {
		if (this.y2 == null || this.y == null) {
			return null;
		}
		return this.y2 - this.y;
	};
	
	@Override
	@XmlAttribute
    @XmlJavaTypeAdapter(ColorAdapter.class)
	public Color getColor() {
		return this.color;
	}


	@Override
	public void setColor(Color col) {
		this.color=col;
	}


	@Override
	@XmlAttribute
    @XmlJavaTypeAdapter(ColorAdapter.class)
	public Color getFill() {
		return this.fill;
	}


	@Override
	public void setFill(Color fill) {
		this.color=fill;
	}

	@Override
	public Node render() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@XmlAttribute
    @XmlJavaTypeAdapter(StartAdapter.class)
	public Integer getStart() {
		return this.start;
	}


	@Override
	public void setStart(Integer start) {
		this.start=start;
	}


	@Override
	@XmlAttribute
	public Integer getDuration() {
		return this.duration;
	}


	@Override
	public void setDuration(Integer duration) {
		this.duration=duration;
	}


}
