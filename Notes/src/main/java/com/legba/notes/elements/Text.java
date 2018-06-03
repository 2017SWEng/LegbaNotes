package com.legba.notes.elements;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.legba.notes.elements.base.*;

import javafx.scene.paint.Color;
/**
 * Class is the text element and allows the formatting 
 * of text elements parameters such as setting bold, italic etc...
 * Class extends SlideElement and implements 
 * Transitionable, Colorable and Formatable.
 * If any parameter is set to null its state will not change.
 * @author vc622
 */
//Rich text element
public class Text extends SlideElement implements Transitionable,Colorable,Formatable{
	
	private Integer start;
	private Integer duration;
	private Integer textsize;
	
	private Color color;
	private Color fill;
	
	private String font;
	
	private Boolean italic;
	private Boolean bold;
	private Boolean underline;
	
	private List<Object> contents;

	public Text() {
		super();		
		
		//Default Values
		this.color = javafx.scene.paint.Color.BLACK;
		this.fill = javafx.scene.paint.Color.BLACK;
		this.font = "Times New Roman";
		this.italic = false;
		this.bold = false;
		this.underline = false;
		this.textsize = 12;
		this.contents = new ArrayList<Object>();

	}
	
	public Text(String s) {
		super();
		
		//Default Values
		this.color = javafx.scene.paint.Color.BLACK;
		this.fill = javafx.scene.paint.Color.BLACK;
		this.font = "Times New Roman";
		this.italic = false;
		this.bold = false;
		this.underline = false;
		this.textsize = 12;
		this.contents = new ArrayList<Object>();
		this.contents.add(s);
	}
	
	
	@XmlElementRefs({
    	@XmlElementRef(name="Format", type=Format.class),
    	@XmlElementRef(name="Br", type=Br.class)
	})
	@XmlMixed
	public List<Object> getContents() {
		return this.contents;
	}
	/**
	 * Sets the contents of Text
	 * @param List<Object> contents
	 */
	public void setContents(List<Object> contents) {
		if (contents == null) {
			return;
		}
		this.contents = contents;	
	}

	public void addContents(Object content) {
		this.contents.clear();
		this.contents.add(content);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<this.getContents().size(); i++) {
			
			if (this.getContents().get(i) instanceof String){
				sb.append(this.getContents().get(i));
			}
			else if (this.getContents().get(i) instanceof Format){
				sb.append(((Format)this.getContents().get(i)).getText());
			}
			else if (this.getContents().get(i) instanceof Br){
				sb.append("\n");
			}
			else {
				System.err.println("Error");
			}
		}
		return sb.toString();
	}
	
	@Override
	@XmlAttribute
	public String getFont() {
		return this.font;
	}

	@Override
	/**
	 * Sets Font of Text
	 * @param font
	 */
	public void setFont(String font) {
		if (font == null) {
			return;
		}
		this.font = font;		
	}

	@Override
	@XmlAttribute
	public Boolean getItalic() {
		return this.italic;
	}

	@Override
	/**
	 * Sets text to Italic
	 * @param italic
	 */
	public void setItalic(Boolean italic) {
		if (italic == null) {
			return;
		}
		this.italic = italic;
	}

	@Override
	@XmlAttribute
	public Boolean getBold() {
		return this.bold;
	}

	@Override
	/**
	 * Sets text to Bold
	 * @param bold
	 */
	public void setBold(Boolean bold) {
		if (bold == null) {
			return;
		}
		this.bold = bold;
	}

	@Override
	@XmlAttribute
	public Boolean getUnderline() {
		return this.underline;
	}

	@Override
	/**
	 * Underlines Text
	 * @param underline
	 */
	public void setUnderline(Boolean underline) {
		if (underline == null) {
			return;
		}
		this.underline = underline;		
	}

	@Override
	@XmlAttribute
	public Integer getTextsize() {
		return this.textsize;
	}

	@Override
	/**
	 *Sets size of Text
	 * @param underline
	 */
	public void setTextsize(Integer size) {
		if (size == null) {
			return;
		}
		this.textsize = size;		
	}
	
	@Override
	@XmlAttribute
    @XmlJavaTypeAdapter(ColorAdapter.class)
	public Color getColor() {
		return this.color;
	}
	
	@Override
	/**
	 * Sets color of Text
	 * @param color
	 */
	public void setColor(Color col) {
		if (col == null) {
			return;
		}
		this.color = col;
	}

	@Override
	@XmlAttribute
    @XmlJavaTypeAdapter(ColorAdapter.class)
	public Color getFill() {
		return this.fill;
	}

	@Override
	/**
	 * Sets color of Textfill
	 * @param fill
	 */
	public void setFill(Color fill) {
		if (fill == null) {
			return;
		}
		this.fill = fill;
	}

	@Override
	@XmlAttribute
    @XmlJavaTypeAdapter(StartAdapter.class)
	public Integer getStart() {
		return this.start;
	}

	@Override
	/**
	 * Sets the amount of time before text will appear
	 * @param start time
	 */
	public void setStart(Integer start) {
		if (start == null) {
			return;
		}
		this.start = start;
	}

	@Override
	@XmlAttribute
	public Integer getDuration() {
		return this.duration;
	}

	@Override
	/**
	 * Sets the amount of time before text will disappear
	 * @param duration time
	 */
	public void setDuration(Integer duration) {
		if (duration == null) {
			return;
		}
		this.duration = duration;
	}
	
	/**
	 * Overwrites the current text
	 * @param newText
	 */
	public void overwrite(Text newText) {
		if(newText.getContents() != null) {
			this.setContents(newText.getContents());
		}
		if(newText.getBold() != null){
			this.setBold(newText.getBold());
		}
		if(newText.getItalic() != null) {
			this.setItalic(newText.getItalic());
		}
		if(newText.getUnderline() != null) {
			this.setUnderline(newText.getUnderline());
		}
		if(newText.getTextsize() != null) {
			this.setTextsize(newText.getTextsize());
		}
		if(newText.getColor() != null) {
			this.setColor(newText.getColor());
		}
		if(newText.getFill() != null) {
			this.setFill(newText.getFill());
		}
		if(newText.getDuration() != null) {
			this.setDuration(newText.getDuration());
		}
		if(newText.getFont() != null) {
			this.setFont(newText.getFont());
		}
		if(newText.getStart() != null) {
			this.setStart(newText.getStart());
		}
	}
}
