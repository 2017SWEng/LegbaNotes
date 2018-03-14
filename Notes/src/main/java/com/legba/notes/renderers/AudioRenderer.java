package com.legba.notes.renderers;

import com.legba.notes.elements.Audio;
import com.legba.notes.nodes.AudioPlayer;

import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
/**
 * Class is the renderer for the Audio class.
 * The class contains 1 method which returns a vbox.  
 * @author lm1370 tmm522
 *
 */
public class AudioRenderer extends Renderer<Audio> {
	
	/**Returns a hbox object which can then be painted on the screen.
	 * The hbox contains 3 children; 
	 * - Text which displays the retrieved file path
	 * - Button which when pressed, plays the media
	 * - Mediaviewer that views the player which plays the media
	 * <p>
	 *  The method always returns immediately, whether or not the audio
	 *  path exists.
	 * 
	 * @param 	audio	The Audio model class to be rendered.
	 * @return 	hbox	GUI 
	 */
	private Duration duration;
	
	public Node render (Audio audio) {
		
		//Play, pause, stop, scrubbing bar
		
		// Setup text
		//Text t = new Text();
		//t.setFont(new Font(20));
		//t.setText(audio.getPath()); 
		
		// Setup mediaplayer
		Media media = new Media(new File(audio.getPath()).toURI().toString());        
        AudioPlayer audioPlayer = new AudioPlayer(media);

		return audioPlayer;
	}
	
	
}
