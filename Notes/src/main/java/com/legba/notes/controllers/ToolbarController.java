package com.legba.notes.controllers;

import com.legba.notes.elements.Shape;
import com.legba.notes.elements.Text;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Font;

/**
 * Controller for the toolbar
 * @author lm1370, tmm522
 *
 */
public class ToolbarController {
	
	public Shape CurrentShape;
	public Text CurrentText;
	public Pane CurrentPane;
	
	@FXML
	private BorderPane toolbar_root;		//Toolbar borderpane
	@FXML
	public ToggleButton boldFont;			//Bold button
	@FXML
	public ToggleButton italicFont;			//Italic button
	@FXML
	public ToggleButton undFont;			//Underline button
	@FXML
	public ColorPicker textColor;			//Text colour wheel
	@FXML
	public ColorPicker textFill;			//Text highlighting wheel
	@FXML 
	public Button pageBreak;				//Page break button
	@FXML
	public ComboBox<String> fontCombo;		//Font type list
	@FXML
	public ComboBox<Integer> sizeCombo;		//Font size list
	@FXML 
	public Button deleteText;				//Delete current text button
	@FXML
	public ComboBox<String> typeCombo;		//Shape type list
	@FXML
	public ComboBox<Integer> strokeCombo;	//Shape stroke width list
	@FXML
	public ColorPicker strokeColor;			//Shape stroke colour wheel
	@FXML
	public CheckBox strokeGradient;			//Shape stroke gradient yes or no
	@FXML
	public ColorPicker strokeColor2;		//Shape stroke colour 2 wheel for gradient
	@FXML
	public ColorPicker shapeFill;			//Shape Fill colour wheel
	@FXML
	public CheckBox fillGradient;			//Shape fill gradient yes or no
	@FXML
	public ColorPicker shapeFill2;			//Shape Fill colour 2 wheel for gradient
	@FXML 
	public Button deleteShape;				//Delete current shape button
	
	/**
	 * Toggles bold font for the selected text
	 * @param event
	 */
	@FXML 
	protected void handleBoldFontAction(ActionEvent event) {
		CurrentText.setBold(boldFont.isSelected());
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Toggles italic font for the selected text 
	 * @param event
	 */
	@FXML 
	protected void handleItalicFontAction(ActionEvent event) {
		CurrentText.setItalic(italicFont.isSelected());
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Toggles underline font for the selected text
	 * @param event
	 */
	@FXML 
	protected void handleUndFontAction(ActionEvent event) {
		CurrentText.setUnderline(undFont.isSelected());
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Sets text colour for the selected text
	 * @param event
	 */
	@FXML 
	protected void handleFontColorAction(ActionEvent event) {
		CurrentText.setColor(strokeColor.getValue());
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Sets text Fill for the selected text
	 * @param event
	 */
	@FXML
	protected void handleFontFillAction(ActionEvent event) {
		CurrentText.setFill(strokeColor.getValue());
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Button to insert a page break 
	 * @param event
	 */
	@FXML 
	protected void handlePageBreakAction(ActionEvent event) {
		System.out.println("Page Break");	
		//TODO: Functionality code....
	}
	
	/**
	 * Takes selected font type and sets it to selected text
	 * @param event
	 */
	@FXML 
	protected void handleFontAction(ActionEvent event) {
		CurrentText.setFont(fontCombo.getValue());
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Takes selected font size and sets it to the selected text
	 * @param event
	 */
	@FXML 
	protected void handleSizeAction(ActionEvent event) {
		CurrentText.setTextsize(sizeCombo.getValue());
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Button to delete currently selected text 
	 * @param event
	 */
	@FXML 
	protected void handleDeleteTextAction(ActionEvent event) {
		CurrentPane.getChildren().remove(CurrentText);
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Takes selected shape type and sets it to the selected shape
	 * @param event
	 */
	@FXML 
	protected void handleShapeTypeAction(ActionEvent event) {
		CurrentShape.setType(typeCombo.getValue());
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Takes selected shape stroke width and sets it to the selected shape
	 * @param event
	 */
	@FXML 
	protected void handleStrokeTypeAction(ActionEvent event) {	
		CurrentShape.setStroke(strokeCombo.getValue());
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Takes selected stroke colour and sets it to the selected shape
	 * @param event
	 */
	@FXML
	protected void handleStrokeColorAction(ActionEvent event) {
		if (strokeGradient.isSelected()) {
			CurrentShape.setColor(new LinearGradient(CurrentShape.getX(), CurrentShape.getY(), CurrentShape.getX2(), CurrentShape.getY2(), false, CycleMethod.NO_CYCLE, getStops(strokeColor.getValue(), strokeColor2.getValue())));
		}
		else {
			CurrentShape.setColor(strokeColor.getValue());	
		}
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Whether or not there should be a gradient for stroke
	 * @param event
	 */
	@FXML
	protected void handleStrokeGradientAction(ActionEvent event) {
		if (strokeGradient.isSelected()) {
			strokeColor2.setDisable(false);
			CurrentShape.setColor(new LinearGradient(CurrentShape.getX(), CurrentShape.getY(), CurrentShape.getX2(), CurrentShape.getY2(), false, CycleMethod.NO_CYCLE, getStops(strokeColor.getValue(), strokeColor2.getValue())));
		}
		else {
			strokeColor2.setDisable(true);
			CurrentShape.setColor(strokeColor.getValue());
		}
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Takes selected stroke colour 2 and sets it to the selected shape
	 * @param event
	 */
	@FXML
	protected void handleStrokeColor2Action(ActionEvent event) {
		if (strokeGradient.isSelected()) {
			CurrentShape.setColor(new LinearGradient(CurrentShape.getX(), CurrentShape.getY(), CurrentShape.getX2(), CurrentShape.getY2(), false, CycleMethod.NO_CYCLE, getStops(strokeColor.getValue(), strokeColor2.getValue())));
		}
		else {
			CurrentShape.setColor(strokeColor.getValue());	
		}
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Takes selected shape fill colour and sets it to the selected shape
	 * @param event
	 */
	@FXML
	protected void handleShapeFillAction(ActionEvent event) {
		if (fillGradient.isSelected()) {
			CurrentShape.setFill(new LinearGradient(CurrentShape.getX(), CurrentShape.getY(), CurrentShape.getX2(), CurrentShape.getY2(), false, CycleMethod.NO_CYCLE, getStops(shapeFill.getValue(), shapeFill2.getValue())));
		}
		else {
			CurrentShape.setFill(shapeFill.getValue());
		}
		AppController.getInstance().viewing.updateSlide();
	}
	/**
	 * Whether or not there should be a gradient for stroke
	 * @param event
	 */
	@FXML
	protected void handleFillGradientAction(ActionEvent event) {
		if (fillGradient.isSelected()) {
			shapeFill2.setDisable(false);
			CurrentShape.setFill(new LinearGradient(CurrentShape.getX(), CurrentShape.getY(), CurrentShape.getX2(), CurrentShape.getY2(), false, CycleMethod.NO_CYCLE, getStops(shapeFill.getValue(), shapeFill2.getValue())));
		}
		else {
			shapeFill2.setDisable(true);
			CurrentShape.setFill(shapeFill.getValue());
		}
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Takes selected shape fill colour 2 and sets it to the selected shape
	 * @param event
	 */
	@FXML
	protected void handleShapeFill2Action(ActionEvent event) {
		if (fillGradient.isSelected()) {
			CurrentShape.setFill(new LinearGradient(CurrentShape.getX(), CurrentShape.getY(), CurrentShape.getX2(), CurrentShape.getY2(), false, CycleMethod.NO_CYCLE, getStops(shapeFill.getValue(), shapeFill2.getValue())));
		}
		else {
			CurrentShape.setFill(shapeFill.getValue());
		}
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Button to delete currently selected shape 
	 * @param event
	 */
	@FXML 
	protected void handleDeleteShapeAction(ActionEvent event) {
		CurrentPane.getChildren().remove(CurrentShape);
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Enables editing tools for shapes and disables others
	 */
	public void shapeMode() {
		boldFont.setDisable(true);
		italicFont.setDisable(true);
		undFont.setDisable(true);
		fontCombo.setDisable(true);
		sizeCombo.setDisable(true);
		pageBreak.setDisable(true);
		textColor.setDisable(true);
		textFill.setDisable(true);
		deleteText.setDisable(true);
		
		typeCombo.setDisable(false);
		strokeCombo.setDisable(false);
		strokeColor.setDisable(false);
		shapeFill.setDisable(false);
		deleteShape.setDisable(false);
		strokeGradient.setDisable(false);
		fillGradient.setDisable(false);
		
		if (CurrentShape.getColor() instanceof LinearGradient) {
			strokeGradient.setSelected(true);
			strokeColor2.setDisable(false);
		}
		else {
			strokeGradient.setSelected(false);
			strokeColor2.setDisable(true);
		}
		
		if (CurrentShape.getFill() instanceof LinearGradient) {
			fillGradient.setSelected(true);
			shapeFill2.setDisable(false);
		}
		else {
			fillGradient.setSelected(false);
			shapeFill2.setDisable(true);
		}

	}
	
	/**
	 * Enables editing tools for text and disables others
	 */
	public void textMode() {
		boldFont.setDisable(false);
		italicFont.setDisable(false);
		undFont.setDisable(false);
		fontCombo.setDisable(false);
		sizeCombo.setDisable(false);
		pageBreak.setDisable(false);
		textColor.setDisable(false);
		textFill.setDisable(false);
		deleteText.setDisable(false);
		
		typeCombo.setDisable(true);
		strokeCombo.setDisable(true);
		strokeColor.setDisable(true);
		shapeFill.setDisable(true);
		deleteShape.setDisable(true);
		strokeGradient.setDisable(true);
		fillGradient.setDisable(true);
		strokeColor2.setDisable(true);
		shapeFill2.setDisable(true);
	}
	
	/**
	 * On Start up disable all editing tools
	 */
	public void initialStartup() {
		boldFont.setDisable(true);
		italicFont.setDisable(true);
		undFont.setDisable(true);
		fontCombo.setDisable(true);
		sizeCombo.setDisable(true);
		pageBreak.setDisable(true);
		textColor.setDisable(true);
		textFill.setDisable(true);
		
		typeCombo.setDisable(true);
		strokeCombo.setDisable(true);
		strokeColor.setDisable(true);
		shapeFill.setDisable(true);
		strokeGradient.setDisable(true);
		fillGradient.setDisable(true);
		strokeColor2.setDisable(true);
		shapeFill2.setDisable(true);
	}

	/**
	 * Initialise method
	 */
	public void initialize() {
		AppController.getInstance().toolbar = this;
		
		fontCombo.getItems().setAll(Font.getFamilies());
		sizeCombo.getItems().setAll(6, 8, 10, 12, 14, 16, 18, 20, 22, 24);
		typeCombo.getItems().setAll("ellipse", "rectangle", "line");
		strokeCombo.getItems().setAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		//Initially disable editing toolbar
		initialStartup();
	}
	
	public Stop[] getStops(Color color1, Color color2) {
		Stop[] stops = new Stop[] {new Stop(0, color1), new Stop(1, color2)};
		return stops;
	}
}
