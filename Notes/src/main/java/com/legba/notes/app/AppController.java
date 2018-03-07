package com.legba.notes.app;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.text.Text;

public class AppController{
	
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
    
	private static AppController instance;
	
	private AppController(){
		// Called before all variable with @FXML have been populated
	}
	
	public static AppController getInstance() {
		if (instance != null){
			instance = new AppController();
		}
		return instance;
	}
	
	public void initialize() {
		// Called once all variable with @FXML have been populated
	}
	
	@FXML 
	protected void handleHomeButtonAction(ActionEvent event) {
		actiontarget.setText("Home button pressed");
	}
	

}
