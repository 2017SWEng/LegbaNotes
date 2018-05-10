package com.legba.notes.controllers;

import com.legba.notes.elements.Presentation;
import com.legba.notes.models.AppModel;
import com.legba.notes.nodes.PdfView;
import com.legba.notes.renderers.PresentationRenderer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Controller for the viewing screen containing notes and PDF viewer
 * @author lm1370 and ...
 *
 */
public class ViewingController {
	
	public Node CurrentNode;
	
	@FXML
	private SplitPane viewing_root;
	
	@FXML
	private VBox reference_root;
	
	@FXML
	private VBox notes_root;
	
	@FXML 
	public Text actiontarget;
	
	/**
	 * Returns slide size index
	 * @return
	 */
	public double[] slideSize() {
		double[] slideSizeIndex;
		
		VBox slideBox = ((VBox)((ScrollPane)notes_root.getChildren().get(0)).getContent());
		
		slideSizeIndex = new double[slideBox.getChildren().size()];
		
		for(int i = 0; i<slideBox.getChildren().size(); i++){
			if (slideBox.getChildren().get(i) instanceof Text){
				Text Slide = ((Text)slideBox.getChildren().get(i));
				slideSizeIndex[i] = Slide.getLayoutBounds().getHeight();
			}
			if (slideBox.getChildren().get(i) instanceof Pane){
				Pane Slide = ((Pane)slideBox.getChildren().get(i));
				slideSizeIndex[i] = Slide.getLayoutBounds().getHeight();
			}
			slideSizeIndex[i] += 10;
			//System.out.println("Slide Height is: " + Slide.getLayoutBounds().getHeight());
		}
		return slideSizeIndex;
		
	}
	
	/**
	 * Enables scrolling on slides
	 * @param slideIndex
	 */
	public void scrollToSlide(int slideIndex){
		
		double[] slideLengths = slideSize();
		
		if (slideIndex > slideLengths.length -1){
			slideIndex = slideLengths.length -1;
		}

		VBox slideBox = ((VBox)((ScrollPane)notes_root.getChildren().get(0)).getContent());
		double totalSlideSize = slideBox.getHeight();
		
		double nextSLideHeight = 0;
		for(int i = 0; i <= slideIndex; i++){
			nextSLideHeight+=slideLengths[i];
		}
		
		System.out.println("totalSlideSize: " + totalSlideSize);
		System.out.println("nextSLideHeight: " + nextSLideHeight);
		System.out.println("actual scroll size: " + nextSLideHeight/totalSlideSize);
		
		((ScrollPane)notes_root.getChildren().get(0)).setVvalue(nextSLideHeight/totalSlideSize);
		
	}
	
	/**
	 * Updates all slides when changes are made from the toolbar controller
	 */
	public void updateSlide() {
		/*-----------------------------------------------------------------------------------------
		TODO: Currently this clears and rebuilds the entire presentation when are changes are made
			  to the text or shapes (size, colour, font, etc.). There might be some way to update
			  without having to rebuild the entire thing every time. - lm1370
			  
		------------------------------------------------------------------------------------------*/
		//Get scroll
		double currentScroll = ((ScrollPane)notes_root.getChildren().get(0)).getVvalue();
		
		// get the presentation from the model
		Presentation pres = AppModel.getInstance().getPres();
		
		// render the presentation
		PresentationRenderer pr = new PresentationRenderer();
		
		// display the presentation
		notes_root.getChildren().clear();
		notes_root.getChildren().add(pr.render(pres));
		
		((ScrollPane)notes_root.getChildren().get(0)).setVvalue(currentScroll);
		
		/*-----------------------------------------------------------------------------------------
		TODO: Fix highlighting issue, when you click on any object it updates the presentation
			  therefore removing the highlighting. Tried to implement it then after updating 
			  but still not working. Not major issue so have left for now - lm1370
		
		//Highlight shape when clicked
		DropShadow dropShadow = new DropShadow();
		dropShadow.setBlurType(BlurType.GAUSSIAN);
		dropShadow.setColor(Color.BLACK);
		dropShadow.setOffsetX(0.0);
		dropShadow.setOffsetY(0.0);
		dropShadow.setRadius(20.0);
		CurrentNode.setEffect(dropShadow);
		
		------------------------------------------------------------------------------------------*/
	}
	
	/**
	 * Constructor
	 */
	public ViewingController(){
		
	}
	
	/**
	 * Initialise method
	 */
 	@FXML
    void initialize(){
		// Called once all variable with @FXML have been populated
 		AppController.getInstance().viewing = this;

 		// get the presentation from the model
		Presentation pres = AppModel.getInstance().getPres();
		
		// render the presentation
		PresentationRenderer pr = new PresentationRenderer();

		// display the presentation
		notes_root.getChildren().clear();
		notes_root.getChildren().add(pr.render(pres));
		
		PdfView pdfView = new PdfView("https://courses.physics.illinois.edu/phys580/fa2013/uncertainty.pdf".toString());
		reference_root.getChildren().clear();
		reference_root.getChildren().add(pdfView);
		
	}
}
