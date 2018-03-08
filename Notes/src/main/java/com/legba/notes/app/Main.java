package com.legba.notes.app;
	
import java.io.IOException;

import com.legba.notes.controllers.AppController;
import com.legba.notes.models.AppModel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;




public class Main extends Application {
	
	static AppController appController;
	static AppModel appModel;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		appController = AppController.getInstance();
		appModel = AppModel.getInstance();
		
		
		//Let's set up some aspects of the UI
		FXMLLoader loader = new FXMLLoader(getClass().getResource("app.fxml"));
		loader.setController(appController);
		Parent root = loader.load();
		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("Images/NotesVeve-Center.png")));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	


	

}
