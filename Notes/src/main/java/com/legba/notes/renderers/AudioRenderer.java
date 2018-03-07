package com.legba.notes.renderers;

import com.legba.notes.elements.Audio;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import javafx.event.ActionEvent;
/**
 * Class is the renderer for the Audio class.
 * The class contains 1 method which returns a vbox.  
 * @author lm1370 tmm522
 *
 */
public class AudioRenderer extends Renderer<Audio> {
	
	/**Returns a vbox object which can then be painted on the screen.
	 * The Vbox contains 3 children; 
	 * - Text which displays the retrieved file path
	 * - Button which when pressed, plays the media
	 * - Mediaviewer that views the player which plays the media
	 * <p>
	 *  The method always returns immediately, whether or not the audio
	 *  path exists.
	 * 
	 * @param 	audio	The Audio model class to be rendered.
	 * @return 	vbox	GUI 
	 */
	public Node render (Audio audio) {
		
		// Setup text
		Text t = new Text();
		t.setFont(new Font(20));
		t.setText(audio.getPath()); 
		
		// Setup mediaplayer
		Media media = new Media(new File(audio.getPath()).toURI().toString());
	    MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

	    // Setup play button
		Button btn1 = new Button("Play");
		//TODO: move this to a controller class
	    btn1.setOnAction((ActionEvent e) -> {
	    	mediaPlayer.play();
	    	System.out.println("Button press");
	    });
	    
	    // Make vertical box and add items to it
	    VBox vbox = new VBox(8); // spacing = 8
	    vbox.getChildren().add(t); 
	    vbox.getChildren().add(btn1);
	    vbox.getChildren().add(mediaView);

		return vbox;
	}
}
