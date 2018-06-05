package com.legba.notes.controllers;

import com.legba.notes.elements.Shape;
import com.legba.notes.elements.Text;
import com.legba.notes.app.HTMLConverter;

import java.io.File;
import java.util.List;

import com.legba.notes.elements.*;
import com.legba.notes.models.AppModel;

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
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Controller for the toolbar
 * @author lm1370, tmm522, ruth.herd
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
	public Presentation CurrentPres;
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
	public ColorPicker textFill;			//Text highlighting wheel
	@FXML
	public CheckBox textFillGradient;		//Text fill gradient yes or no
	@FXML
	public ColorPicker textFill2;			//Text highlighting wheel 2
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
	public CheckBox strokeGradient;			//Shape stroke gradient yes or no
	@FXML
	public ColorPicker strokeColor2;		//Shape stroke colour 2 wheel for gradient
	@FXML
	public ColorPicker shapeFill;			//Shape Fill colour wheel
	@FXML
	public HTMLEditor insertText;			//Text field for text input 
	@FXML
	public Button insertTextButton;			//Button for submitting text changes
	@FXML
	public CheckBox fillGradient;			//Shape fill gradient yes or no
	@FXML
	public ColorPicker shapeFill2;			//Shape Fill colour 2 wheel for gradient
	@FXML 
	public Button deleteElement;			//Delete current shape button
	@FXML 
	public ComboBox<String> addCombo;		//Add element button
	@FXML
	public Button syncSlide;				//Sync slide to time
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
	 * Sets text Fill for the selected text
	 * @param event
	 */
	@FXML
	protected void handleFontFillAction(ActionEvent event) {
		if (textFillGradient.isSelected()) {
			CurrentText.setFill(new LinearGradient(CurrentText.getX(), CurrentText.getY(), CurrentText.getX2(), CurrentText.getY2(), false, CycleMethod.NO_CYCLE, getStops(textFill.getValue(), textFill2.getValue())));
		}
		else {
			CurrentText.setFill(textFill.getValue());
		}
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Whether there should be a gradient for fill colour
	 * @param event
	 */
	@FXML
	protected void handleTextFillGradientAction(ActionEvent event) {
		if (textFillGradient.isSelected()) {
			textFill2.setDisable(false);
			CurrentText.setFill(new LinearGradient(CurrentText.getX(), CurrentText.getY(), CurrentText.getX2(), CurrentText.getY2(), false, CycleMethod.NO_CYCLE, getStops(textFill.getValue(), textFill2.getValue())));
		}
		else {
			textFill2.setDisable(true);
			CurrentText.setFill(textFill.getValue());
		}
		AppController.getInstance().viewing.updateSlide();
	}
	
	/**
	 * Sets 2nd text colour for selected text
	 * @param event
	 */
	@FXML
	protected void handleTextFill2Action(ActionEvent event) {
		if (textFillGradient.isSelected()) {
			CurrentText.setFill(new LinearGradient(CurrentText.getX(), CurrentText.getY(), CurrentText.getX2(), CurrentText.getY2(), false, CycleMethod.NO_CYCLE, getStops(textFill.getValue(), textFill2.getValue())));
		}
		else {
			CurrentText.setFill(textFill.getValue());
		}
		AppController.getInstance().viewing.updateSlide();
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
				//Open and format the file viewer
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Audio");
				fileChooser.getExtensionFilters().addAll(
						new ExtensionFilter("Audio Files", "*.wav", "*.WAV", "*.aif", "*.AIF", "*.aiff", "*.AIFF", "*.m3u8", "*.M3U8", "*.mp3", "*.MP3")
				);
				
				//Set Directory to workspace
				String userDirectoryString = System.getProperty("user.dir");
				File userDirectory = new File(userDirectoryString);
				fileChooser.setInitialDirectory(userDirectory);
				
				//Get audio from file chooser
				File audioToOpen = fileChooser.showOpenDialog(AppController.getInstance().getMainStage());
				
				//Create audio from chosen path from file chooser
				Audio a = new Audio(audioToOpen.getName());
				CurrentSlide.addAudio(a);
				AddElement = true;
				
			} else if(SelectedItem.equals("Video")) {
				//Open and format the file viewer
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Video");
				fileChooser.getExtensionFilters().addAll(
						new ExtensionFilter("Audio Files", "*.mp4", "*.MP4", "*.m4a", "*.M4A", "*.m4v", "*.M4V", "*.m3u8", "*.M3U8", "*.fxm", "*.FXM", "*.flv", "*.FLV")	
				);
				
				//Set Directory to workspace
				String userDirectoryString = System.getProperty("user.dir");
				File userDirectory = new File(userDirectoryString);
				fileChooser.setInitialDirectory(userDirectory);
				
				//Get video from file chooser
				File videoToOpen = fileChooser.showOpenDialog(AppController.getInstance().getMainStage());
				
				//Create video from chosen path from file chooser
				Video v = new Video(videoToOpen.getName(), 10, 35, 500, 400);
				CurrentSlide.addVideo(v);
				AddElement = true;
				
			}	else if(SelectedItem.equals("Image")) {
				//Open and format the file viewer
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open image");
				fileChooser.getExtensionFilters().addAll(
						new ExtensionFilter("Audio Files", "*.jpeg", "*.JPEG", "*.jpg", "*.JPG", "*.gif", "*.GIF", "*.png", "*.PNG", "*.bmp", "*.BMP")	
				);
				
				//Set Directory to workspace
				String userDirectoryString = System.getProperty("user.dir");
				File userDirectory = new File(userDirectoryString);
				fileChooser.setInitialDirectory(userDirectory);
				
				//Get image from file chooser
				File imageToOpen = fileChooser.showOpenDialog(AppController.getInstance().getMainStage());
								
				//Create image from chosen path from file chooser
				Image i = new Image(imageToOpen.getName(), 10, 35, 500, 400);
				CurrentSlide.addImage(i);
				
			}	else if(SelectedItem.equals("Slide")) {
				CurrentPres = AppModel.getInstance().getPres();
				Slide newSlide = new Slide();
				CurrentPres.addSlide(newSlide);
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
	 * Takes text typed into the text area and inserts it into the slide.
	 * @param event
	 */
	@FXML
	protected void handleInsertTextAction(ActionEvent event) {
		//Get HTML text
		String htmlText = insertText.getHtmlText();
		
		List<Text> slideTexts = CurrentSlide.getTexts();
		slideTexts.remove(CurrentText);
		
		Text t = HTMLConverter.toPWS(htmlText);
		
		if (t != null){
			slideTexts.add(t);
			CurrentSlide.setTexts(slideTexts);
		
			//Update slides
			AppController.getInstance().viewing.updateSlide();
		}
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
		textFill.setDisable(true);

		textFillGradient.setDisable(true);
		textFill2.setDisable(true);
		
		typeCombo.setDisable(false);
		strokeCombo.setDisable(false);
		strokeColor.setDisable(false);
		shapeFill.setDisable(false);

		syncSlide.setDisable(true);

		insertText.setHtmlText(null);
		insertText.setDisable(true);
		insertTextButton.setDisable(true);

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
		textFill.setDisable(false);

		textFillGradient.setDisable(false);
		typeCombo.setDisable(true);
		strokeCombo.setDisable(true);
		strokeColor.setDisable(true);
		shapeFill.setDisable(true);
		
		

		insertText.setDisable(false);
		if(CurrentText != null) {
			insertText.setHtmlText(HTMLConverter.toHTML(CurrentText));
		}
		insertTextButton.setDisable(false);
		
		

		strokeGradient.setDisable(true);
		fillGradient.setDisable(true);
		strokeColor2.setDisable(true);
		shapeFill2.setDisable(true);
		
		if (CurrentText.getFill() instanceof LinearGradient) {
			textFillGradient.setSelected(true);
			textFill2.setDisable(false);
		}
		else {
			textFillGradient.setSelected(false);
			textFill2.setDisable(true);
		}

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

		textFill.setDisable(true);
		typeCombo.setDisable(true);
		strokeCombo.setDisable(true);
		strokeColor.setDisable(true);
		shapeFill.setDisable(true);
		
		strokeGradient.setDisable(true);
		fillGradient.setDisable(true);
		strokeColor2.setDisable(true);
		shapeFill2.setDisable(true);
		
		syncSlide.setDisable(false);
		
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
		textFill.setDisable(true);
		textFillGradient.setDisable(true);
		textFill2.setDisable(true);
		
		typeCombo.setDisable(true);
		strokeCombo.setDisable(true);
		strokeColor.setDisable(true);
		shapeFill.setDisable(true);

		insertText.setDisable(true);
		insertText.setPrefSize(200, 100);
		insertTextButton.setDisable(true);

		strokeGradient.setDisable(true);
		fillGradient.setDisable(true);
		strokeColor2.setDisable(true);
		shapeFill2.setDisable(true);
		deleteElement.setDisable(true);
		addCombo.setDisable(true);
		
		syncSlide.setDisable(true);
		scrollVideo.setDisable(false);
	}

	
	/**
	 * Sets all the tooltips for all the options on the toolbar
	 */
	protected void buttonTooltips() {
		//Bold Font
		final Tooltip boldFontTT = new Tooltip();
		boldFontTT.setText(
			"Toggles the selected text\n" +
		    "bold status on and off.\n" 
		);
		boldFont.setTooltip(boldFontTT);
		
		//Italic Font
		final Tooltip italicFontTT = new Tooltip();
		italicFontTT.setText(
			"Toggles the selected text\n" +
		    "italic status on and off.\n" 
		);
		italicFont.setTooltip(italicFontTT);
		
		//Underline Font
		final Tooltip undFontTT = new Tooltip();
		undFontTT.setText(
			"Toggles the selected text\n" +
		    "underline status on and off.\n" 
		);
		undFont.setTooltip(undFontTT);
		
		//Font Combo
		final Tooltip fontComboTT = new Tooltip();
		fontComboTT.setText(
			"Sets the font of the\n" +
		    "selected text.\n" 
		);
		fontCombo.setTooltip(fontComboTT);
		
		//Size Combo
		final Tooltip sizeComboTT = new Tooltip();
		sizeComboTT.setText(
			"Sets the size of the\n" +
		    "selected text.\n" 
		);
		sizeCombo.setTooltip(sizeComboTT);
		
		//Text Fill
		final Tooltip textFillTT = new Tooltip();
		textFillTT.setText(
			"Sets the primary colour\n" +
		    "of the selected text.\n" 
		);
		textFill.setTooltip(textFillTT);
		
		//Text Fill Gradient
		final Tooltip textFillGradientTT = new Tooltip();
		textFillGradientTT.setText(
			"Enable to allow the selected\n" +
		    "text color to gradiented.\n" 
		);
		textFillGradient.setTooltip(textFillGradientTT);
		
		//Text Fill 2
		final Tooltip textFill2TT = new Tooltip();
		textFill2TT.setText(
			"Sets the secondary colour\n" +
		    "of the selected text.\n"  
		);
		textFill2.setTooltip(textFill2TT);
		
		//Type Combo
		final Tooltip typeComboTT = new Tooltip();
		typeComboTT.setText(
			"Sets the type of\n" +
		    "the selected shape.\n"
		);
		typeCombo.setTooltip(typeComboTT);
		
		//Stroke Combo
		final Tooltip strokeComboTT = new Tooltip();
		strokeComboTT.setText(
			"Sets the size of the border\n" +
		    "for the selected shape.\n"
		);
		strokeCombo.setTooltip(strokeComboTT);
		
		//Stroke Color
		final Tooltip strokeColorTT = new Tooltip();
		strokeColorTT.setText(
			"Sets the primary colour\n" +
		    "of the selected shape's border.\n"  
		);
		strokeColor.setTooltip(strokeColorTT);
		
		//Shape Fill
		final Tooltip shapeFillTT = new Tooltip();
		shapeFillTT.setText(
			"Sets the primary colour\n" +
		    "of the selected shape.\n"
		);
		shapeFill.setTooltip(shapeFillTT);
		
		//Stroke Gradient
		final Tooltip strokeGradientTT = new Tooltip();
		strokeGradientTT.setText(
			"Enable to allow the selected\n" +
		    "shape's border to gradiented.\n" 
		);
		strokeGradient.setTooltip(strokeGradientTT);
		
		//Shape Fill Gradient
		final Tooltip shapeFillGradientTT = new Tooltip();
		shapeFillGradientTT.setText(
			"Enable to allow the selected\n" +
		    "shape color to gradiented.\n" 
		);
		fillGradient.setTooltip(shapeFillGradientTT);
		
		//Stroke Color 2
		final Tooltip strokeColor2TT = new Tooltip();
		strokeColor2TT.setText(
			"Sets the secondary colour\n" +
		    "of the selected shape's border.\n"
		);
		strokeColor2.setTooltip(strokeColor2TT);
		
		//Shape Fill 2
		final Tooltip shapeFill2TT = new Tooltip();
		shapeFill2TT.setText(
			"Sets the secondary colour\n" +
		    "of the selected shape.\n"
		);
		shapeFill2.setTooltip(shapeFill2TT);
		
		//Delete element
		final Tooltip deleteElementTP = new Tooltip();
		deleteElementTP.setText(
			"Deletes the selected \n" +
		    "element from the slide.\n" 
		);
		deleteElement.setTooltip(deleteElementTP);
		
		//Add Combo
		final Tooltip addComboTP = new Tooltip();
		addComboTP.setText(
			"Choose an element from the list\n" +
		    "to add to the selected slide.\n" 
		);
		addCombo.setTooltip(addComboTP);			
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
		addCombo.getItems().setAll("Shape", "Text", "Audio", "Video", "Image", "Slide");
		scrollVideo.setSelected(true);
		
		//Set listeners on all buttons to display tooltips
		buttonTooltips();
		
		//Initially disable editing toolbar
		initialStartup();
	}
	

	public Stop[] getStops(Color color1, Color color2) {
		Stop[] stops = new Stop[] {new Stop(0, color1), new Stop(1, color2)};
		return stops;
	}
}
