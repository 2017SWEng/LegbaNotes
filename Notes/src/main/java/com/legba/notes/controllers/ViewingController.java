package com.legba.notes.controllers;

import java.io.File;

import com.legba.notes.elements.Presentation;
import com.legba.notes.elements.Slide;
import com.legba.notes.models.AppModel;
import com.legba.notes.nodes.PdfView;
import com.legba.notes.renderers.PresentationRenderer;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
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
		
		reference_root.getChildren().clear();
			
		PdfView pdfView = new PdfView("https://courses.physics.illinois.edu/phys580/fa2013/uncertainty.pdf".toString());

		reference_root.getChildren().add(pdfView);
		
	}
 	
 	
 	

}
