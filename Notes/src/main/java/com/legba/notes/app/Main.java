package com.legba.notes.app;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
		appController.setMainStage(primaryStage);
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
		
		try {
		    // Try to get LOCK //
		    if (!AppLock.setLock("MY_CUSTOM_LOCK_KEY")) {
		        System.out.println("Only one application instance may run at the same time!");
		        
		        //Display warning message
		        warn();
		        System.exit(1);
		    }
		
		//Run program
		launch(args);
		
		}
		finally{
		    AppLock.releaseLock(); // Release lock
		}
	}
	
	/**
	 * Warning message when trying to open program when it's already running
	 */
	private static void warn() {
        final JFrame parent = new JFrame();

        JOptionPane.showMessageDialog(parent,
    		    "Legba Notes Is Already Running",
    		    "Warning",
    		    JOptionPane.WARNING_MESSAGE);
    }
	
}
