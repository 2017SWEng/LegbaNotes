package com.legba.notes.elements.base;


// All text formatting options (except color).
// If these attributes are omitted on an element,
// it will inherit them from its parent
public interface Formatable {
	

	//The font family to be used. If the specified family is not available, the implementor may define a fallback.
	public String getFont();

	public void setFont(String font);

	
	//Whether the text should be italic.
	public Boolean getItalic();

	public void setItalic(Boolean italic);

	
	//Whether the text should be bold.
	public Boolean getBold();

	public void setBold(Boolean bold);

	
	//Whether the text should be underlined.
	public Boolean getUnderline();

	public void setUnderline(Boolean underline);

	
	//The size (in points)
	public Integer getTextsize();

	public void setTextsize(Integer size);
}
