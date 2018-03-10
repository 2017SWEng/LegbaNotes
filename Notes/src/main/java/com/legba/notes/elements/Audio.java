package com.legba.notes.elements;

import com.legba.notes.elements.base.*;
/**
 * Class sets audio file path and checks if it is valid.
 * Class extends MultiMediaElement which contains the
 * get file path method.
 * @author lm1370 tmm522
 *
 */
public class Audio extends MultiMediaElement{
	
	/**
	 * Constructor supers element
	 */
	protected Audio() {
		super();
	}
	
	/**
	 * Method sets audio path and through super, 
	 * calls isValidPath to check validity of path.
	 * @param path Filepath 
	 */
	public Audio(String path) {
		super();

		this.setPath(path);
	}
	
	/**
	 * Boolean method that checks the filepath is 
	 * in the correct format and that is length is
	 * larger than zero. If both these requirements
	 * are met, method returns TRUE.
	 * If either are not met, returns FALSE.
	 * @param path Filepath
	 */
	@Override
	protected boolean isValidPath(String path) {
		
		if (
			path.length() > 0 && 
			path.endsWith(".wav")
		) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Audio [" + (getPath() != null ? "getPath()=" + getPath() + ", " : "")
				+ (getX() != null ? "getX()=" + getX() + ", " : "") + (getY() != null ? "getY()=" + getY() + ", " : "")
				+ (getX2() != null ? "getX2()=" + getX2() + ", " : "") + (getY2() != null ? "getY2()=" + getY2() : "")
				+ "]";
	}
}
