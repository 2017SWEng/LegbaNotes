package com.legba.notes.elements;


// All text formatting options (except color).
// If these attributes are omitted on an element,
// it will inherit them from its parent
public class Formats extends AttributeGroup {
	
	public Formats(Element element) {
		super(element);
		
		this.setFont("Courier");
		this.setItalic(false);
		this.setBold(false);
		this.setUnderlined(false);
		this.setSize(12);
	}
	
	//The font family to be used. If the specified family is not available, the implementor may define a fallback.
	public String getFont() {
		return (String)this.element.getAttribute("font");

	}

	public void setFont(String font) {
		this.element.setAttribute("font", font);
	}

	//Whether the text should be italic.
	public boolean isItalic() {
		return (boolean)this.element.getAttribute("italic");
	}

	public void setItalic(boolean italic) {
		this.element.setAttribute("italic", italic);
	}

	//Whether the text should be bold.
	public boolean isBold() {
		return (boolean)this.element.getAttribute("bold");

	}

	public void setBold(boolean bold) {
		this.element.setAttribute("bold", bold);		
	}

	//Whether the text should be underlined.
	public boolean isUnderlined() {
		return (boolean)this.element.getAttribute("underlined");

	}

	public void setUnderlined(boolean underlined) {
		this.element.setAttribute("underlined", underlined);		
	}

	//The size (in points)
	public int getSize() {
		return (int)this.element.getAttribute("size");
	}

	public void setSize(int size) {
		this.element.setAttribute("size", size);				
	}
}
