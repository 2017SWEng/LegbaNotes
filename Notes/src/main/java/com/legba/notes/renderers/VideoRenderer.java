package com.legba.notes.renderers;

import com.legba.notes.elements.Video;
import com.legba.notes.nodes.MovieView;

import javafx.scene.Node;

public class VideoRenderer extends Renderer<Video>{

	@Override
	/**
	 * Takes a video and renders it
	 * @param video The video to be rendered
	 * @return returns a Node with the rendered mediaplayer
	 */
	public Node render(Video video) {
		MovieView movieView = new MovieView(video.getPath(), video.getX(), video.getY(), video.getWidth(), video.getHeight());
        return movieView;
	}

}
