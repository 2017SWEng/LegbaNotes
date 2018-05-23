package com.legba.notes.elements;


import com.legba.notes.elements.base.*;
/**
 * Class sets image file path and checks if it is valid.
 * Class extends MultiMediaElement which contains the
 * get file path method.
 * @author vc622
 *
 */
public class Image extends MultiMediaElement {
	
	/**
	 * Constructor supers element
	 */	
	protected Image() {
		super();
	};
	
	/**
	 * Method sets image path and through super, 
	 * calls isValidPath to check validity of path.
	 * @param path Filepath 
	 */
	public Image(String path) {
		super();

		this.setPath(path);
		
	}
	/**
	 * Boolean method that checks the filepath is 
	 * in the correct format and that its length is
	 * larger than zero. If both these requirements
	 * are met, method returns TRUE.
	 * If either are not met, returns FALSE.
	 * Accepted formats referenced from javafx.scene.image
	 * @param path Filepath
	 */
	@Override
	protected boolean isValidPath(String path) {
		if (
			path.length() > 0 && 
			(path.endsWith(".jpeg")|| 
			 path.endsWith(".JPEG")||
			 path.endsWith(".jpg") ||
			 path.endsWith(".JPG") ||
			 path.endsWith(".gif") ||
			 path.endsWith(".GIF") ||
			 path.endsWith(".png") ||
			 path.endsWith(".PNG") ||
			 path.endsWith(".bmp") ||
			 path.endsWith(".BMP")
			)
		) {
			return true;
		}
		
		return false;
	}
}
