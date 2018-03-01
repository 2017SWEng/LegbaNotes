package com.legba.notes.app;
	
import java.io.File;
import java.io.IOException;

import com.legba.notes.elements.Audio;
import com.legba.notes.renderers.AudioRenderer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;



public class Main extends Application {
	
	static AppController appController;
	static AppModel appModel;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		//Let's set up some aspects of the UI
		
		Parent root = FXMLLoader.load(getClass().getResource("appVeiw.fxml"));
		
		//appController.initialize();
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		test();
	}
	
	
	public static void main(String[] args) {
		launch(args);

	}
	
	public void test() {
		
		Audio audio = new Audio ("testData/audioTest.wav");
		AudioRenderer audiorend = new AudioRenderer(); 
		String audioPath = audio.getPath();
	    Media sound = new Media(new File(audioPath).toURI().toString());
	    MediaPlayer mediaPlayer = new MediaPlayer(sound);
		
		VBox n = (VBox) audiorend.render(audio);
		Button btn1 = new Button("play");
		n.getChildren().add(btn1);
		if(n!= null)
			System.out.println("YAY");
		if (n.getChildren().size()==2)
			System.out.println("TWO CHILDREN YAY");
		else
			System.out.println("TWO CHILDREN NAY");
		
		
		
		btn1.fire();
		Status playing = mediaPlayer.getStatus();
		
		System.out.println(playing);
			
	}
	

}
