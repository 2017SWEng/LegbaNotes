package com.legba.notes.app;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.image.*;
import javafx.scene.text.Text;

public class ToolbarController {
	
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
	private ComboBox<String> fontCombo;
	
	@FXML
	private ComboBox<String> sizeCombo;
	
	@FXML
	private ColorPicker textColor;
    
	@FXML 
	protected void handleStartButtonAction(ActionEvent event) {
		actiontarget.setText("Start button pressed");
	}
	
	@FXML 
	protected void handleColorPickAction(ActionEvent event) {
		actiontarget.setText("Color chosen: " + textColor.getValue());	
	}
	
	public void initialize() {
		//File file = new File("/Images/Logo-Hozizontal.png");
		//Logo = new Image(file.toURI().toString());
		fontCombo.getItems().setAll("font1", "font3", "font619");
		sizeCombo.getItems().setAll("8", "10", "12");
	}
}
