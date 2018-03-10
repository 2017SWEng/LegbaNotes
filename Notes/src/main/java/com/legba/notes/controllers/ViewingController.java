package com.legba.notes.controllers;

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
		Presentation pres = AppModel.getInstance().getPres();
		PresentationRenderer pr = new PresentationRenderer();

		notes_root.getChildren().clear();
		notes_root.getChildren().add(pr.render(pres));
		
		
	}
 	

}
