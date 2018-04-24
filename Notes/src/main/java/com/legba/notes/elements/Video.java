package com.legba.notes.elements;

import com.legba.notes.elements.base.*;

public class Video extends MultiMediaElement{
	
	protected Video() {
		super();
	};
	
	public Video(String path) {
		super();

		this.setPath(path);
		
	}
	
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
