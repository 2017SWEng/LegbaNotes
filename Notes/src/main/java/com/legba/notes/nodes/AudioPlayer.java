package com.legba.notes.nodes;

import com.legba.notes.controllers.AppController;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.layout.Priority;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class AudioPlayer extends BorderPane {
	
	private MediaPlayer mediaPlayer;
	private Duration duration;
	private Slider timeSlider;
	
	public AudioPlayer(Media media, double x, double y, double width, double height) {
		
		mediaPlayer = new MediaPlayer(media);

		// Setup play button
		Button playButton = new Button("Play");
		playButton.setOnAction((ActionEvent e) -> {
			mediaPlayer.play();
			System.out.println("Play");
		});
	
		// Setup pause button
		Button pauseButton = new Button("Pause");
		pauseButton.setOnAction((ActionEvent e) -> {
			mediaPlayer.pause();
			System.out.println("Pause");
		});
	
		// Add time slider
		timeSlider = new Slider();
		HBox.setHgrow(timeSlider,Priority.ALWAYS);
		timeSlider.setMinWidth(50);
		timeSlider.setMaxWidth(Double.MAX_VALUE);
		
		timeSlider.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				if (timeSlider.isValueChanging()) {
					// multiply duration by percentage calculated by slider position
					mediaPlayer.setStartTime(new Duration(0));
					mediaPlayer.seek(duration.multiply(timeSlider.getValue() / 100.0));
		       	}
		    }
		});
	   
		mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() 
		{
			public void invalidated(Observable ov) {
				updateValues();
			}
		});
	 	   
		mediaPlayer.setOnReady(new Runnable() {
			public void run() {
				duration = mediaPlayer.getMedia().getDuration();
				updateValues();
			}
		});
		
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(new Duration(0));
				mediaPlayer.pause();
				updateValues();
			}
		});
		  
		MediaView mediaView = new MediaView (mediaPlayer);
		
		// Make horizontal box and add items to it
		HBox hbox = new HBox(8); // spacing = 8
	    hbox.getChildren().add(playButton);
	    hbox.getChildren().add(pauseButton);
	    
	    // Add the Hbox to this, which is a borderPane
	    this.setLeft(hbox);
	    hbox.getChildren().add(timeSlider);
	    this.setCenter(timeSlider);
	    this.setTop(mediaView);
	    
	    //Set coordinates
	    this.setLayoutX(x);
	    this.setLayoutY(y);
	    this.setPrefSize(width, height);
	    
	    // Add media player to list of total
        AppController.getInstance().viewing.allMediaPlayers.add(mediaPlayer);
	}
	
	protected void updateValues() {

		Platform.runLater(new Runnable() {
		    public void run() {
			    Duration currentTime = mediaPlayer.getCurrentTime();
			          
			    timeSlider.setDisable(duration.isUnknown());
			    if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
			    	timeSlider.setValue((currentTime.toMillis()/duration.toMillis())* 100.0);
		    	}       
	    	}
		});
	  
	}

} 
