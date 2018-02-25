package com.legba.notes.elements.base;


// All text formatting options (except color).
// If these attributes are omitted on an element,
// it will inherit them from its parent
public interface Formatable {
	

	//The font family to be used. If the specified family is not available, the implementor may define a fallback.
	public String getFont();

	public void setFont(String font);

	
	//Whether the text should be italic.
	public boolean isItalic();

	public void setItalic(boolean italic);

	
	//Whether the text should be bold.
	public boolean isBold();

	public void setBold(boolean bold);

	
	//Whether the text should be underlined.
	public boolean isUnderlined();

	public void setUnderlined(boolean underlined);

	
	//The size (in points)
	public int getSize();

	public void setSize(int size);
}
