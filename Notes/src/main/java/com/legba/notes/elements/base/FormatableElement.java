package com.legba.notes.elements.base;

public abstract class FormatableElement extends Element implements Formatable {

	private String font;
	private boolean italic;
	private boolean bold;
	private boolean underlined;
	private int size;
	
	public FormatableElement(String tagName) {
		super(tagName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getFont() {
		return this.font;
	}


	@Override
	public void setFont(String font) {
		//TODO:: Check to make sure font is valid
		this.font=font;		
	}


	@Override
	public boolean isItalic() {
		return this.italic;
	}


	@Override
	public void setItalic(boolean italic) {
		this.italic=italic;
	}


	@Override
	public boolean isBold() {
		return this.bold;
	}


	@Override
	public void setBold(boolean bold) {
		this.bold=bold;
	}


	@Override
	public boolean isUnderlined() {
		return this.underlined;
	}


	@Override
	public void setUnderlined(boolean underlined) {
		this.underlined=underlined;		
	}


	@Override
	public int getSize() {
		return this.size;
	}


	@Override
	public void setSize(int size) {
		this.size=size;		
	}
}
