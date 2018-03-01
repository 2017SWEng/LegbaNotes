package com.legba.notes.renderers;

import com.legba.notes.elements.Audio;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.event.ActionEvent;

public class AudioRenderer {
 
	public Node render (Audio audio) {
		
		String audioPath = audio.getPath();
		
		Text t = new Text();
		t.setFont(new Font(20));
		t.setText(audioPath); 
		Button btn1 = new Button("play");
		
		VBox vbox = new VBox(8); // spacing = 8
	    vbox.getChildren().add(t); 
	    vbox.getChildren().add(btn1);
	    
	    Media sound = new Media(new File(audioPath).toURI().toString());
	    MediaPlayer mediaPlayer = new MediaPlayer(sound);
	    
	    btn1.setOnAction((ActionEvent e) -> {
	    	mediaPlayer.play();
	    	System.out.println("Button press");
	    });
	    		
	     
		return vbox;
	}
}
