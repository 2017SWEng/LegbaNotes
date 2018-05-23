package com.legba.notes.elements;

import com.legba.notes.elements.base.*;
/**
 * Class sets video file path and checks if it is valid.
 * Class extends MultiMediaElement which contains the
 * get file path method.
 * @author vc622 
 */
public class Video extends MultiMediaElement{
	
	protected Video() {
		super();
	};
	/**
	 * Method sets video path and through super, 
	 * calls isValidPath to check validity of path.
	 * @param path Filepath 
	 */
	public Video(String path) {
		super();

		this.setPath(path);
		
	}
	/**
	 * Boolean method that checks the filepath is 
	 * in the correct format and that its length is
	 * larger than zero. If both these requirements
	 * are met, method returns TRUE.
	 * If either are not met, returns FALSE.
	 * Accepted formats referenced from javafx.scene.media
	 * @param path Filepath
	 */
	@Override
	protected boolean isValidPath(String path) {
		
		if (
			path.length() > 0 && 
			(
					path.endsWith(".mp4") ||//MPEG-4 Part 14
					path.endsWith(".MP4") ||
					path.endsWith(".m4a") ||
					path.endsWith(".M4A") ||
					path.endsWith(".m4v") ||
					path.endsWith(".M4V") ||
					path.endsWith(".m3u8")||//MP2T HTTP Live Streaming (audiovisual)
					path.endsWith(".M3U8")||
					path.endsWith(".fxm") ||//FX Media
					path.endsWith(".FXM") ||
					path.endsWith(".flv") ||//Flash Video
					path.endsWith(".FLV")
			)
		) {
			return true;
		}
		
		return false;
	}

}
