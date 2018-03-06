package com.legba.notes.renderers;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import com.legba.notes.elements.Audio;
import com.legba.notes.elements.Shape;
import com.legba.notes.elements.Slide;

public class SlideRenderer implements Renderer<Slide> {

	VectorRenderer vectorRenderer;
	AudioRenderer audioRenderer;
	//TODO: add other renders to this

	
	public SlideRenderer(){
		this.vectorRenderer = new VectorRenderer();
		this.audioRenderer = new AudioRenderer();
		//TODO: add other renders to this
	}
	
	public SlideRenderer(
			VectorRenderer vectorRenderer,
			AudioRenderer audioRenderer
			){
		this.vectorRenderer = vectorRenderer;
		this.audioRenderer = audioRenderer;
		//TODO: add other renders to this
	}
	
	@Override
	public Node render(Slide s) {

		
		
		Pane pane =  new Pane();

		for(Shape shape : s.getShapes()){
			pane.getChildren().add(this.vectorRenderer.render(shape));
		}
		
		for(Audio audio : s.getAudios()){
			pane.getChildren().add(this.audioRenderer.render(audio));
		}
		
		//TODO: repeat for other renderers
		
		
		return pane;
	}

}
