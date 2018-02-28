package com.legba.notes.app;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.image.*;
import javafx.scene.text.Text;

public class AppController {
	
	@FXML
    private BorderPane root;
	
	@FXML
    private VBox content_root;
	
	@FXML
    private VBox reference_root;
	
	@FXML
	private BorderPane topbar_root;
	
	@FXML 
	private Text actiontarget;
	
	@FXML
	private Image Logo;
    
    @FXML 
    protected void handleHomeButtonAction(ActionEvent event) {
        actiontarget.setText("Home button pressed");
    }
	
	public void initialize() {
		//File file = new File("/Images/Logo-Hozizontal.png");
	    //Logo = new Image(file.toURI().toString());
    }
}
