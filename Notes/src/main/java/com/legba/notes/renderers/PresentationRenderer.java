package com.legba.notes.renderers;

import javafx.scene.layout.VBox;
import javafx.scene.Node;

import java.util.List;

import com.legba.notes.elements.Presentation;
import com.legba.notes.elements.Slide;

/**
 * Take a instance of the Presentation class and produces a java fx node tree
 * @author zraw500
 */
public class PresentationRenderer implements Renderer<Presentation>{

	SlideRenderer slideRenderer;
	
	/**
	 * Default constructor uses the default slide renderer
	 */
	public PresentationRenderer() {
		this.slideRenderer = new SlideRenderer();
	}
	
	/**
	 * Constructor that takes a custom slide render for the presentation renderer
	 * @param slideRenderer The renderer to use to render Slide elements
	 */
	public PresentationRenderer(SlideRenderer slideRenderer) {
		this.slideRenderer = slideRenderer;
	}
	
	/**
	 * Renderers the presentation passed to it
	 * @param pres the presentation to render
	 */
	@Override
	public Node render(Presentation pres) {
				
		VBox vbox = new VBox();
		
		List<Slide> slide = pres.getSlide();
		
		for(Slide s : slide){
			vbox.getChildren().add(slideRenderer.render(s));
		}
		
		return vbox;
	}

}
