package com.legba.notes.nodes;

import com.legba.notes.controllers.AppController;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.css.PseudoClass;
import javafx.scene.layout.Priority;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AudioPlayer extends BorderPane {
	
	private MediaPlayer mediaPlayer;
	private Duration duration;
	private Slider timeSlider;
	private Label label;
	private Button playPauseButton;
	private double labelTime;
	
	
	public AudioPlayer(Media media, double x, double y, double width, double height) {
		
		mediaPlayer = new MediaPlayer(media);
		playPauseButton = new Button();
		label = new Label();
		
		
		getStyleClass().add("unlock--movieview");
		
		playPauseButton.getStyleClass().add("button--playpause");
		updatePlayingState();
		
		// Toggle playing
        playPauseButton.setOnAction(e -> {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
                mediaPlayer.pause();
            else
                mediaPlayer.play();
        });
        
        // Need to set the listener to update the :playing pseudo-class
        mediaPlayer.statusProperty().addListener(obs ->
            updatePlayingState());
	
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
		hbox.getChildren().add(playPauseButton);
		hbox.getChildren().add(timeSlider);
		hbox.getChildren().add(label);
	    
	    // Add the Hbox to this, which is a borderPane
	    this.setLeft(hbox);
	    this.setCenter(timeSlider);
	    this.setTop(mediaView);
	    
	    //Set coordinates
	    this.setLayoutX(x);
	    this.setLayoutY(y);
	    this.setPrefSize(width, height);
	    
	    // Add media player to list of total
        AppController.getInstance().viewing.allMediaPlayers.add(mediaPlayer);
	}
	
	/**
	 * Updates the slider to run in real-time
	 */
	protected void updateValues() {
		
		labelTime = Math.round((timeSlider.getValue() / 10) * 10d) / 10d;
        label.setText(Double.toString(labelTime));
        label.setTextFill(Color.WHITE);
        label.setTranslateY(3.5);

        
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
	
    /**
     * Link with the stylesheet in /resources
     * @return
     */
    @Override public String getUserAgentStylesheet() {
        return getClass().getClassLoader().getResource("com/legba/notes/nodes/css/MovieView.css").toExternalForm();
    }
    
    /**
     * Updates the state of the :playing pseudo-class
     */
    private void updatePlayingState() {
        pseudoClassStateChanged(
                PseudoClass.getPseudoClass("playing"),
                mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING
        );
    }

} 
