package com.legba.notes.controllers;

import java.util.List;

import com.legba.notes.elements.Audio;
import com.legba.notes.elements.Image;
import com.legba.notes.elements.Shape;
import com.legba.notes.elements.Slide;
import com.legba.notes.elements.Text;
import com.legba.notes.elements.Video;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
	public Audio CurrentAudio;
	public Video CurrentVideo;
	public Image CurrentImage;
	public String CurrentElement;
	public Slide CurrentSlide;
	public boolean AddElement;
	
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
	public ComboBox<String> typeCombo;		//Shape type list
	@FXML
	public ComboBox<Integer> strokeCombo;	//Shape stroke width list
	@FXML
	public ColorPicker strokeColor;			//Shape stroke colour wheel
	@FXML
	public ColorPicker shapeFill;			//Shape Fill colour wheel
	@FXML 
	public Button deleteElement;			//Delete current shape button
	@FXML 
	public ComboBox<String> addCombo;		//Add element button
	@FXML
	public Button syncSlide;				//Synce slide to time
	@FXML
	public CheckBox scrollVideo;			//Scroll with video
	
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
		CurrentText.setColor(textColor.getValue());
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Sets text Fill for the selected text
	 * @param event
	 */
	@FXML
	protected void handleFontFillAction(ActionEvent event) {
		CurrentText.setFill(textFill.getValue());
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
		CurrentShape.setColor(strokeColor.getValue());
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Takes selected shape fill colour and sets it to the selected shape
	 * @param event
	 */
	@FXML
	protected void handleShapeFillAction(ActionEvent event) {
		CurrentShape.setFill(shapeFill.getValue());
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Takes selected shape fill colour and sets it to the selected shape
	 * @param event
	 */
	@FXML
	protected void handlescrollVideoAction(ActionEvent event) {

	}
	
	/**
	 * Takes selected shape fill colour and sets it to the selected shape
	 * @param event
	 */
	@FXML
	protected void handlesyncSlideAction(ActionEvent event) {
		Double duration = AppController.getInstance().viewing.getReferenceRootTime();
		if (duration != null) {
			CurrentSlide.setDuration((int)Math.round(duration));
			AppController.getInstance().viewing.updateSlide();
		}
	}
	
	/**
	 * Button to delete currently selected element 
	 * @param event
	 */
	@FXML 
	protected void handleDeleteElementAction(ActionEvent event) {
		//Delete selected element
		try {
			if(CurrentElement.equals("Shape")) {
				//Delete shape
				List<Shape> slideShapes = CurrentSlide.getShapes();
				slideShapes.remove(CurrentShape);
				CurrentSlide.setShapes(slideShapes);
				
			} else if(CurrentElement.equals("Text")) {
				//Delete text
				List<Text> slideTexts = CurrentSlide.getTexts();
				slideTexts.remove(CurrentText);
				CurrentSlide.setTexts(slideTexts);
				
			} else if(CurrentElement.equals("Audio")) {
				//Delete Audio
				List<Audio> slideAudios = CurrentSlide.getAudios();
				slideAudios.remove(CurrentAudio);
				CurrentSlide.setAudios(slideAudios);
				
			} else if(CurrentElement.equals("Video")) {
				//Delete Video
				List<Video> slideVideos = CurrentSlide.getVideos();
				slideVideos.remove(CurrentVideo);
				CurrentSlide.setVideos(slideVideos);
				
			} else if(CurrentElement.equals("Image")) {
				//Delete Image
				List<Image> slideImages = CurrentSlide.getImages();
				slideImages.remove(CurrentImage);
				CurrentSlide.setImages(slideImages);
				
			}
		} catch(Exception ex) {
			//Do nothing
		}
				
		//Update presentation
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Button to add elements
	 * @param event
	 */
	@FXML
	protected void handleAddItemAction(ActionEvent event) {
		//Get combo box value		
		try {
			String SelectedItem = addCombo.getValue();
			
			if(SelectedItem.equals("Shape")) {
				//Default shape is ellipse
				Shape s = new Shape("ellipse");
				s.setX2(50f);
				s.setY2(50f);
				s.setFill(Color.WHITE);
				s.setStroke(5);
				s.setColor(Color.BLACK);
				s.setX(10f);
				s.setY(7f);
				CurrentSlide.addShape(s);
				
			} else if(SelectedItem.equals("Text")) {
				//TODO: Need text box editing code from luke
				Text t = new Text("Example text");
				CurrentSlide.addText(t);
				
			} else if(SelectedItem.equals("Audio")) {
				//Default audio file
				Audio a = new Audio("testData/audioTest.wav");
				CurrentSlide.addAudio(a);
				AddElement = true;
				
			} else if(SelectedItem.equals("Video")) {
				//Default video file
				Video v = new Video("local_file.mp4", 10, 35, 500, 400);
				CurrentSlide.addVideo(v);
				AddElement = true;
				
			}	else if(SelectedItem.equals("Image")) {
				//Default image file
				Image i = new Image("local_file.jpg", 10, 35, 500, 400);
				CurrentSlide.addImage(i);
				
			}

		} catch(Exception ex) {
			//Do nothing as the add combo box is always reset to null when selecting a slide
			//So that you add multiple elements of the same type on one slide without having
			//to select another type
		}
		
		//Add element to current slide
		AppController.getInstance().viewing.updateSlide();
		AddElement = false;
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
		
		typeCombo.setDisable(false);
		strokeCombo.setDisable(false);
		strokeColor.setDisable(false);
		shapeFill.setDisable(false);
		
		syncSlide.setDisable(true);
		
		paneMode();
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
		
		typeCombo.setDisable(true);
		strokeCombo.setDisable(true);
		strokeColor.setDisable(true);
		shapeFill.setDisable(true);
		
		syncSlide.setDisable(true);
		
		paneMode();
	}
	
	/**
	 * Enables editing tools for slides and disables others
	 */
	public void slideMode() {
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
		
		syncSlide.setDisable(false);
		
		addCombo.setDisable(true);
		deleteElement.setDisable(true);
	}
	
	/**
	 * Enables addition of elements onto current pane
	 */
	public void paneMode() {
		addCombo.setDisable(false);
		deleteElement.setDisable(false);
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
		deleteElement.setDisable(true);
		addCombo.setDisable(true);
		
		syncSlide.setDisable(true);
		scrollVideo.setDisable(false);
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
		addCombo.getItems().setAll("Shape", "Text", "Audio", "Video", "Image");
		
		//Initially disable editing toolbar
		initialStartup();
	}
}
