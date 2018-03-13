package com.legba.notes.app;

import java.io.File;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

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

	//Bold font -- Display current state of bold
	@FXML
	private ToggleButton boldFont;
	@FXML 
	protected void handleBoldFontAction(ActionEvent event) {
		actiontarget.setText("Bold: " + boldFont.isSelected());
	}
	/*-----------------------------------*/
	
	//Italic font -- Display current state of Italic
	@FXML
	private ToggleButton italicFont;
	@FXML 
	protected void handleItalicFontAction(ActionEvent event) {
		actiontarget.setText("Italic: " + italicFont.isSelected());
	}
	/*-----------------------------------*/
	
	//Underline font -- Display current state of underline
	@FXML
	private ToggleButton undFont;
	@FXML 
	protected void handleUndFontAction(ActionEvent event) {
		actiontarget.setText("Underline: " + undFont.isSelected());
	}
	/*-----------------------------------*/
	
	//Start button -- Display current state of start
	@FXML 
	protected void handleStartButtonAction(ActionEvent event) {
		actiontarget.setText("Start button pressed");
	}
	/*-----------------------------------*/
	
	//Text colour wheel -- Display current selected color
	@FXML
	private ColorPicker textColor;
	@FXML 
	protected void handleColorPickAction(ActionEvent event) {
		actiontarget.setText("Color chosen: " + textColor.getValue());	
	}
	/*-----------------------------------*/
	
	//Page Break -- Display current state of page break
	@FXML 
	protected void handlePageBreakAction(ActionEvent event) {
		actiontarget.setText("Page Break");	
	}
	/*-----------------------------------*/
	
	//Font -- Display current chosen font
	@FXML
	private ComboBox<String> fontCombo;
	@FXML 
	protected void handleFontAction(ActionEvent event) {
		actiontarget.setText("Font: " + fontCombo.getValue());	
	}
	/*-----------------------------------*/
	
	//Size -- Display current chosen size
	@FXML
	private ComboBox<String> sizeCombo;
	@FXML 
	protected void handleSizeAction(ActionEvent event) {
		actiontarget.setText("Size: " + sizeCombo.getValue());	
	}
	/*-----------------------------------*/
	
	public void initialize() {
		fontCombo.getItems().setAll(Font.getFontNames());
		sizeCombo.getItems().setAll("8", "10", "12");
	}
}
