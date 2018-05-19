package com.legba.notes.renderers;

import com.legba.notes.elements.Audio;
import com.legba.notes.nodes.AudioPlayer;

import javafx.scene.Node;
import javafx.scene.media.Media;

import java.io.File;

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
