package com.legba.notes.renders;

import javafx.scene.layout.VBox;
import javafx.scene.Node;

import com.legba.notes.elements.Presentation;
import com.legba.notes.elements.Slide;

public class PresentaionRenderer implements Renderer<Presentation>{

	SlideRenderer slideRenderer;
	
	public PresentaionRenderer() {
		this.slideRenderer = new SlideRenderer();
	}
	
	public PresentaionRenderer(SlideRenderer slideRenderer) {
		this.slideRenderer = slideRenderer;
	}
	
	@Override
	public Node render(Presentation e) {
		
		this.slideRenderer = new SlideRenderer();
		
		VBox vbox = new VBox();
		
		Slide[] slide = (Slide[]) e.getSlide().toArray();
		
		for(Slide s : slide){
			vbox.getChildren().add(slideRenderer.render(s));
		}
		
		return vbox;
	}

}
