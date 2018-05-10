package com.legba.notes.elements;

import com.legba.notes.elements.base.*;
/**
 * Class sets audio file path and checks if it is valid.
 * Class extends MultiMediaElement which contains the
 * get file path method.
 * @author vc622 lm1370 tmm522
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
			path.endsWith(".wav") || //Waveform Audio Format
			path.endsWith(".WAV") ||
			path.endsWith(".aif") || //Audio Interchange File Format
			path.endsWith(".AIF") ||
			path.endsWith(".aiff")|| 
			path.endsWith(".AIFF")||
			path.endsWith(".m3u8")||//MP3 HTTP Live Streaming (audiovisual)
			path.endsWith(".M3U8")||
			path.endsWith(".mp3") ||//MPEG-1, 2, 2.5 raw audio stream possibly with ID3 metadata v2.3 or v2.4
			path.endsWith(".MP3")
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
