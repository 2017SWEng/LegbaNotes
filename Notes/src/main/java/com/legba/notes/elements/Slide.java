package com.legba.notes.elements;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.legba.notes.elements.base.*;

import javafx.scene.paint.Color;

public class Slide extends Element implements Transitionable,Colorable,Formatable{
	
	private Color[] color;
	
	private Color[] fill;
	
	private String font;
	
	private Boolean italic;
	
	private Boolean bold;
	
	private Boolean underline;
	
	private Integer size;
	
	private Integer start;
	
	private Integer duration;
	
	List<Audio> audios;
	List<Image> images;
	List<Shape> shapes;
	List<Text> texts;
	List<Video> videos;
	
	public Slide() {
		super();
		
		this.audios = new ArrayList<Audio>();
		this.images = new ArrayList<Image>();
		this.shapes = new ArrayList<Shape>();
		this.texts = new ArrayList<Text>();
		this.videos = new ArrayList<Video>();
	}
	
	@XmlElement(name="Audio")
	public List<Audio> getAudios() {
		return audios;
	}

	public void setAudios(List<Audio> audios) {
		this.audios = audios;
	}
	
	public void addAudio(Audio audio) {
		this.audios.add(audio);
	}

	@XmlElement(name="Image")
	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void addAudio(Image image) {
		this.images.add(image);
	}

	@XmlElement(name="Shape")
	public List<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}
	
	public void addShape(Shape shape) {
		this.shapes.add(shape);
	}

	@XmlElement(name="Text")
	public List<Text> getTexts() {
		return texts;
	}

	public void setTexts(List<Text> texts) {
		this.texts = texts;
	}

	public void addText(Text text) {
		this.texts.add(text);
	}
	
	@XmlElement(name="Video")
	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
	public void addVideo(Video video) {
		this.videos.add(video);
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

	@Override
    @XmlJavaTypeAdapter(ColorAdapter.class)
	@XmlAttribute
	public Color[] getColor() {
		return this.color;
	}


	@Override
	public void setColor(Color[] col) {
		this.color=col;
	}


	@Override
    @XmlJavaTypeAdapter(ColorAdapter.class)
	@XmlAttribute
	public Color[] getFill() {
		return this.fill;
	}


	@Override
	public void setFill(Color[] fill) {
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
