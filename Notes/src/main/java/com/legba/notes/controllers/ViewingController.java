package com.legba.notes.controllers;

import java.util.ArrayList;
import java.util.List;

import com.legba.notes.elements.Presentation;
import com.legba.notes.models.AppModel;
import com.legba.notes.nodes.PdfView;
import com.legba.notes.renderers.PresentationRenderer;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Controller for the viewing screen containing notes and PDF viewer
 * @author lm1370 and ...
 *
 */
public class ViewingController {
	
	public Node CurrentNode;
	public List<MediaPlayer> allMediaPlayers = new ArrayList<>();
	
	@FXML
	private SplitPane viewing_root;
	
	@FXML
	private VBox reference_root;
	
	@FXML
	private VBox notes_root;
	
	@FXML 
	public Text actiontarget;
	
	/**
	 * Returns slide size index
	 * @return
	 */
	public double[] slideSize() {
		double[] slideSizeIndex;
		
		VBox slideBox = ((VBox)((ScrollPane)notes_root.getChildren().get(0)).getContent());
		
		slideSizeIndex = new double[slideBox.getChildren().size()];
		
		for(int i = 0; i<slideBox.getChildren().size(); i++){
			if (slideBox.getChildren().get(i) instanceof Text){
				Text Slide = ((Text)slideBox.getChildren().get(i));
				slideSizeIndex[i] = Slide.getLayoutBounds().getHeight();
			}
			if (slideBox.getChildren().get(i) instanceof Pane){
				Pane Slide = ((Pane)slideBox.getChildren().get(i));
				slideSizeIndex[i] = Slide.getLayoutBounds().getHeight();
			}
			slideSizeIndex[i] += 10;
			//System.out.println("Slide Height is: " + Slide.getLayoutBounds().getHeight());
		}
		return slideSizeIndex;
		
	}
	
	/**
	 * Enables scrolling on slides
	 * @param slideIndex
	 */
	public void scrollToSlide(int slideIndex){
		
		double[] slideLengths = slideSize();
		
		if (slideIndex > slideLengths.length -1){
			slideIndex = slideLengths.length -1;
		}

		VBox slideBox = ((VBox)((ScrollPane)notes_root.getChildren().get(0)).getContent());
		double totalSlideSize = slideBox.getHeight();
		
		double nextSLideHeight = 0;
		for(int i = 0; i <= slideIndex; i++){
			nextSLideHeight+=slideLengths[i];
		}
		
		System.out.println("totalSlideSize: " + totalSlideSize);
		System.out.println("nextSLideHeight: " + nextSLideHeight);
		System.out.println("actual scroll size: " + nextSLideHeight/totalSlideSize);
		
		((ScrollPane)notes_root.getChildren().get(0)).setVvalue(nextSLideHeight/totalSlideSize);
		
	}
	
	/**
	 * Updates all slides when changes are made from the toolbar controller
	 */
	public void updateSlide() {
		
		//Storage for playback locations and status
		ArrayList<Duration> currentPlayback = new ArrayList<Duration>();
		ArrayList<MediaPlayer.Status> currentStatus = new ArrayList<MediaPlayer.Status>();
			
		//Get current scroll location
		double currentScroll = ((ScrollPane)notes_root.getChildren().get(0)).getVvalue();
		
		// Get current playback locations and current status for all media
		for(MediaPlayer m : this.allMediaPlayers) {
			currentPlayback.add(m.getCurrentTime());
			currentStatus.add(m.getStatus());
		}
			
		// Stop all current playing media and remove media player storage
		stopAllMedia();
		this.allMediaPlayers.clear();
		
		// Get the presentation from the model
		Presentation pres = AppModel.getInstance().getPres();
		
		// Re-render the presentation
		PresentationRenderer pr = new PresentationRenderer();
		
		// Display the presentation
		notes_root.getChildren().clear();
		notes_root.getChildren().add(pr.render(pres));
		
		// Set playback locations and status for all media
		for(MediaPlayer m : this.allMediaPlayers) {
			
			// Set scroll to previous position
			((ScrollPane)notes_root.getChildren().get(0)).setVvalue(currentScroll);	
			
			//If media player was playing, continue playing otherwise stop
			if (currentStatus.get(allMediaPlayers.indexOf(m)) == MediaPlayer.Status.PLAYING)
                m.play();
            else 
                m.stop();
			
			//Set playback position
			m.setStartTime(currentPlayback.get(allMediaPlayers.indexOf(m)));
		}
	}
	
	/**
	 * Sets all media currently playing to "stop"
	 */
	public void stopAllMedia() {
		//For all mediaPlayers rendered, apply stop method
		for(MediaPlayer m : this.allMediaPlayers) {
			m.stop();
		}
	}
	
	/**
	 * Constructor
	 */
	public ViewingController(){
		
	}
	
	/**
	 * Initialise method
	 */
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
		
		PdfView pdfView = new PdfView("https://courses.physics.illinois.edu/phys580/fa2013/uncertainty.pdf".toString());
		reference_root.getChildren().clear();
		reference_root.getChildren().add(pdfView);
		
	}
}
