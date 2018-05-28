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
	
	/**
	 * Creates a media player object using the file specified and returns a rendered
	 * audio player made from that media player.
	 * 
	 *  @Param Audio audio
	 *  @return audioPlayer
	 */
	public Node render (Audio audio) {	
		// Setup mediaplayer
		Media media = new Media(new File(audio.getPath()).toURI().toString());        
        AudioPlayer audioPlayer = new AudioPlayer(media);

		return audioPlayer;
	}
	
	
}
