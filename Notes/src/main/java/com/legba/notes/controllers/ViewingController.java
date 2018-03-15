package com.legba.notes.controllers;


import com.legba.notes.elements.Presentation;
import com.legba.notes.models.AppModel;
import com.legba.notes.nodes.PdfView;
import com.legba.notes.renderers.PresentationRenderer;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ViewingController {

	
	@FXML
	private SplitPane viewing_root;
	
	@FXML
	private VBox reference_root;
	
	@FXML
	private VBox notes_root;
	
	@FXML 
	public Text actiontarget;
	
	public Node CurrentNode;

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
	
	public void scrollToSlide(int slideIndex){
		
		double[] slideLengths = slideSize();
		
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
	
	public ViewingController(){
		
	}
	
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
