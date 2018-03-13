package com.legba.notes.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.legba.notes.elements.Text;
import com.legba.notes.renderers.TextRenderer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;



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
		
	}
	
	
	public static void main(String[] args) {
		launch(args);

	}
	
}
