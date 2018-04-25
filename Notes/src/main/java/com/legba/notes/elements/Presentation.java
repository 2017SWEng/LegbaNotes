package com.legba.notes.elements;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.legba.notes.elements.base.*;

import javafx.scene.paint.Color;

@XmlRootElement(name="Presentation")
public class Presentation extends Element implements Colorable,Formatable{

	
	private Color color;
	
	private Color fill;
	
	private String font;
	
	private Boolean italic;
	
	private Boolean bold;
	
	private Boolean underline;
	
	private Integer size;
	
	private List<Meta> Metas;
	private List<Slide> Slides;
	
	public Presentation() {
		super();

		this.Metas=new ArrayList<Meta>();
		this.Slides=new ArrayList<Slide>();
	}
	
	public void setSlide(List<Slide> slide) {
		this.Slides.clear();
		this.Slides.addAll(slide);
	}
	
	@XmlElement(name="Slide")
	public List<Slide> getSlide() {
		return this.Slides;
	}
	
	
	public void addSlide(Slide slide) {
		this.Slides.add(slide);
	}
	
	public Slide getSlide(int index) {
		if(index < this.Slides.size()) {
			return this.Slides.get(index);
		}
		return null;
	}
	
	public void setMeta(List<Meta> meta) {
		this.Metas.clear();
		this.Metas.addAll(meta);
	}
	
	@XmlElement(name="Meta")
	public List<Meta> getMeta() {
		return this.Metas;
	}
	
	public void addMeta(Meta meta) {
		this.Metas.add(meta);
	}
	
	public Meta getMeta(int index) {
		if(index < this.Metas.size()) {
			return this.Metas.get(index);
		}
		return null;
	}

	@Override
    @XmlJavaTypeAdapter(ColorAdapter.class)
	@XmlAttribute
	public Color getColor() {
		return this.color;
	}


	@Override
	public void setColor(Color col) {
		this.color=col;
	}


	@Override
    @XmlJavaTypeAdapter(ColorAdapter.class)
	@XmlAttribute
	public Color getFill() {
		return this.fill;
	}


	@Override
	public void setFill(Color fill) {
		this.fill=fill;
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
	public Boolean getUnderline() {
		return this.underline;
	}


	@Override
	public void setUnderline(Boolean underline) {
		this.underline=underline;		
	}


	@Override
	@XmlAttribute
	public Integer getTextsize() {
		return this.size;
	}


	@Override
	public void setTextsize(Integer size) {
		this.size=size;		
	}

}
