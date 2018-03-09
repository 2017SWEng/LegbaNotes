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
	
	static AppController ToolbarController;
	static AppModel appModel;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		//Let's set up some aspects of the UI
		
		Parent root = FXMLLoader.load(getClass().getResource("MultimediaToolbar.fxml"));
		
		//appController.initialize();
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);

	}

	

}
