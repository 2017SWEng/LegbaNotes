package com.legba.notes.renderers;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import com.legba.notes.elements.Audio;
import com.legba.notes.elements.Shape;
import com.legba.notes.elements.Slide;

/**
 * Takes an instance of a Slide and produces an javafx Node tree.
 * @author zraw500
 *
 */
public class SlideRenderer extends Renderer<Slide> {

	VectorRenderer vectorRenderer;
	AudioRenderer audioRenderer;
	//TODO: add other renders to this

	/**
	 * Default constructor, use default SlideElement renderers
	 */
	public SlideRenderer(){
		this.vectorRenderer = new VectorRenderer();
		this.audioRenderer = new AudioRenderer();
		//TODO: add other renders to this
	}
	
	/**
	 * Custom constructor, use custom SlideElement renderers
	 * @param vectorRenderer
	 * @param audioRenderer
	 */
	public SlideRenderer(
			VectorRenderer vectorRenderer,
			AudioRenderer audioRenderer
			){
		this.vectorRenderer = vectorRenderer;
		this.audioRenderer = audioRenderer;
		//TODO: add other renders to this
	}
	
	/**
	 * Renders the given slide
	 * @param s Slide to render
	 * @return javafx Node tree
	 */
	@Override
	public Node render(Slide s) {

		
		// using pane because it allows absolute positioning
		Pane pane =  new Pane();
		pane.getStyleClass().add("element-slide");

		
		for(Shape shape : s.getShapes()){
			Node n = this.vectorRenderer.render(shape);
			pane.getChildren().add(n);
		}
		
		for(Audio audio : s.getAudios()){
			pane.getChildren().add(this.audioRenderer.render(audio));
		}
		
		//TODO: repeat for other renderers
		
		
		return pane;
	}

}
