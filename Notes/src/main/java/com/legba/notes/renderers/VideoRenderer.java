package com.legba.notes.renderers;

import com.legba.notes.elements.Video;
import com.legba.notes.nodes.MovieView;

import javafx.scene.Node;

public class VideoRenderer extends Renderer<Video>{

	@Override
	public Node render(Video video) {
		MovieView movieView = new MovieView(video.getPath(), video.getX(), video.getY(), video.getWidth(), video.getHeight());
        return movieView;
	}

}
