package com.legba.notes.renderers;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javax.swing.plaf.PanelUI;

import com.legba.notes.elements.Shape;
import com.legba.notes.elements.Slide;

public class SlideRenderer implements Renderer<Slide> {

	VectorRenderer vectorRenderer;
	//TODO: add other renders to this

	
	public SlideRenderer(){
		this.vectorRenderer = new VectorRenderer();
		//TODO: add other renders to this
	}
	
	public SlideRenderer(VectorRenderer vectorRenderer){
		this.vectorRenderer = vectorRenderer;
		//TODO: add other renders to this
	}
	
	@Override
	public Node render(Slide s) {

		
		
		Pane pane =  new Pane();

		for(Shape shape : s.getShapes()){
			pane.getChildren().add(this.vectorRenderer.render(shape));
		}
		
		//TODO: repeat for other renderers
		
		
		return pane;
	}

}
