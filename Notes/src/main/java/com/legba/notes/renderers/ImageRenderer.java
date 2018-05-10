package com.legba.notes.renderers;

import com.legba.notes.elements.Image;
import com.legba.notes.nodes.PictureView;

import javafx.scene.Node;

public class ImageRenderer extends Renderer<Image>{

	@Override
	public Node render(Image image) {
		PictureView pictureView = new PictureView(image.getPath(), image.getX(), image.getY(), image.getWidth(), image.getHeight());
		
		return pictureView;
	}

}
