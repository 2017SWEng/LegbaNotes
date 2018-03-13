package com.legba.notes.controllers;

import java.io.File;

import com.legba.notes.PDF.PdfView;
import com.legba.notes.elements.Presentation;
import com.legba.notes.models.AppModel;
import com.legba.notes.renderers.PresentationRenderer;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
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
	
	public ViewingController(){
		
	}
	
 	@FXML
    void initialize(){
		// Called once all variable with @FXML have been populated
 		
 		// get the presentation from the model
		Presentation pres = AppModel.getInstance().getPres();
		
		// render the presentation
		PresentationRenderer pr = new PresentationRenderer();

		// display the presentation
		notes_root.getChildren().clear();
		notes_root.getChildren().add(pr.render(pres));
		
		
		PdfView pdfView = new PdfView();
		reference_root.getChildren().clear();
		reference_root.getChildren().add(pdfView.render(("https://courses.physics.illinois.edu/phys580/fa2013/uncertainty.pdf").toString()));
		
	}
 	

}
