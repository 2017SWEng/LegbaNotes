package com.legba.notes.controllers;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

import com.legba.notes.models.AppModel;
import com.legba.notes.models.ViewMode;
import com.legba.notes.models.ViewMode.Mode;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;

public class AppController implements Observer{
	
	@FXML
	public BorderPane root;
    
	private static AppController instance;
	
	public MenuController menu;
	public ViewingController viewing;
	public ToolbarController toolbar;
	public HomepageController homepage;
	public FileSystemController fileSystemController;
	
	private final static String menuPath = "com/legba/notes/fxml/menu.fxml";
	private final static String homepagePath = "com/legba/notes/fxml/homepage.fxml";
	private final static String viewingPath = "com/legba/notes/fxml/viewing.fxml";
	
	private Stage mainStage;
	
	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
		
		//Different view modes equal different exit messages			
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {	
	        @Override
	        public void handle(final WindowEvent event) {
	            //Stage
	            final Stage dialog = new Stage();
	            
	            if(AppModel.getInstance().getVeiwMode() == ViewMode.Mode.VEIWING) {
		            Label label = new Label("Do you want to save your work?");
		            Button saveBtn = new Button("Save and close");
		            Button dontSaveBtn = new Button("Close without saving");
		            Button cancelBtn = new Button("Cancel");
		            
		            HBox closeWindow = new HBox();
		            closeWindow.getChildren().add(label);
		            closeWindow.getChildren().add(saveBtn);
		            closeWindow.getChildren().add(dontSaveBtn);
		            closeWindow.getChildren().add(cancelBtn);
		            
		            dialog.setScene(new Scene(closeWindow));
		            dialog.initModality(Modality.APPLICATION_MODAL);
		            dialog.show();
		            
		            //Save and close program
		            saveBtn.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                	//Call function to save external file
		                	boolean ifFileValid = AppController.getInstance().menu.externalSaveFile();
		                	
		                	//Check that file name is valid / action hasn't been cancelled
		                	if(ifFileValid == true) {
		                		dialog.close();
			                    mainStage.close();
		                	}
		                	else {
		                		System.out.println("File not chosen");
		                		dialog.close();
		                	}
		                	
		                }
		            });
		            
		            //Close without saving program
		            dontSaveBtn.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    dialog.close();
		                    mainStage.close();
		                }
		            });
		            
		            //Close dialog pop up
		            cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    dialog.close();
		                }
		            });
		            
		            event.consume();
		        }
	            else {
		            Label label = new Label("Do you want to exit?");
		            Button yesBtn = new Button("Yes");
		            Button noBtn = new Button("No");
		            
		            HBox closeWindow = new HBox();
		            closeWindow.getChildren().add(label);
		            closeWindow.getChildren().add(yesBtn);
		            closeWindow.getChildren().add(noBtn);
		            
		            dialog.setScene(new Scene(closeWindow));
		            dialog.initModality(Modality.APPLICATION_MODAL);
		            dialog.show();
		            
		            //Close program
		            yesBtn.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                	dialog.close();
		                    mainStage.close();
		                }
		            });
		            
		            //Close without saving program
		            noBtn.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                    dialog.close();
		                }
		            });
		            
		            event.consume();
	            }
	        } 
		});
        mainStage.show();
	}

	private AppController(){
		// Called before all variable with @FXML have been populated
		this.fileSystemController = new FileSystemController();
	}
	
	public static AppController getInstance() {
		if (instance == null){
			instance = new AppController();
		}
		return instance;
	}
	
	public void start(Stage mainStage) throws Exception {
	    mainStage.setOnCloseRequest(e -> {
	        System.out.print("QUIT");
	    });
	    mainStage.show();
	}
	
 	@FXML
    void initialize(){
 		
		// Called once all variable with @FXML have been populated
 		
		// Add menu
		addMenu();
		
		// Add veiwMode observer
		AppModel.getInstance().addVeiwModeObserver(this);
		
		// force updateMode to load default page
		updateMode(ViewMode.Mode.HOMEPAGE);

	}
	
 	
 	// Loads fxml file
 	private Node loadFXML(URL path){
 		System.out.println("[+] Loading " + path);
		
 		Node node = null;
		try {
			node = FXMLLoader.load(path);
		} catch (IOException e) {
			System.err.println("\t[ERR!] Unable to load " + path);
			e.printStackTrace();
		}
		
		System.out.println("[-] Loaded " + path);

		
		return node;
 	}
 	
	private void addMenu() {
		
		// load menu from fxml
		Node menu = loadFXML( getClass().getClassLoader().getResource(menuPath));
		
		// if the loaded menu is not null then set the top to it
		if (menu != null){
			root.setTop(menu);
			System.out.println("\t[ OK ] Set Menu");
		}
		

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		System.out.println("== app update ==");
		
		// check what has called this method
		if (arg0 instanceof ViewMode){
			if (arg1 instanceof Mode){
				updateMode((Mode)arg1);
			}
			else {
				System.err.println("[err!] Veiw mode passed unexpected object type to observers");
			}
		}
		
		//for debugging
		//logNodes(root,0);

	}

	private void updateMode(Mode mode) {
		
		if (mode == null){
			System.err.println(this.toString() + " : mode was null");
			return;
		}
		
		// Build the file path based on the mode
		String path = null;
		
		if (mode == Mode.HOMEPAGE){
			path = AppController.homepagePath;
		}
		else if(mode == Mode.VEIWING){
			path = AppController.viewingPath;
		}
		
		
		// make sure we set a path
		if (path == null){
			System.err.println(this.toString() + " : path was null");
			return;
		}
		
		// load fxml file
		Node n = loadFXML( getClass().getClassLoader().getResource(path));
		
		// if the loaded file exists then set the center to it
		if (n != null){
			root.setCenter(n);
			System.out.println("\t[ OK ] Set Veiw to " + mode.toString());
		}
		
	}
	
 	// TODO: move this to some kind of logger class
 	void logNodes(Node root, int depth) {
		
		//System.out.println("# "+root.toString());
		
		String s = "";
		for(int i = 0; i < depth; i++) {
			s += "|\t";
		}
		s += "L\t";

		System.out.println(s + root);
		
		if (root instanceof SplitPane) {
			for(Node n : ((SplitPane)root).getItems()) {
				logNodes(n,depth+1);
			}
		}
		else if (root instanceof ScrollPane) {
				logNodes(((ScrollPane)root).getContent(),depth+1);
		}
		else if (root instanceof Parent) {
			for(Node n : ((Parent)root).getChildrenUnmodifiable()) {
				logNodes(n,depth+1);
			}

		} 
				
	}
	
 	
 	public void updateRecents(File openedFile) throws IOException{
		//append to the recents file
		Path legbaPath = Paths.get(System.getProperty("user.home") + File.separator + "Legba");
		
		
		if(!Files.exists(legbaPath, LinkOption.NOFOLLOW_LINKS))
		{
			//create the directory
			try{
				File LegbaDir = legbaPath.toFile();
				LegbaDir.mkdir();
				System.out.println("Legba directory created...");
			}
			catch(SecurityException se){
				se.printStackTrace();
			}
		}
		
		//we have the legba directory
		//now to check for the file
		File recentsDoc = new File(legbaPath.toString() + File.separator + "RecentDocs");
		if(!Files.exists(Paths.get(recentsDoc.getPath()), LinkOption.NOFOLLOW_LINKS))
		{
			//create the file
			try{
				recentsDoc.createNewFile();
				System.out.println("Recent Docs File created...");
			}
			catch(SecurityException se){
				se.printStackTrace();
			}
			catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
		
		//we now have everything we need
		//append to the file
		//!! add checking to this !!
		FileWriter fw = new FileWriter(recentsDoc, true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    bw.write(openedFile.getAbsolutePath());
	    bw.newLine();
	    bw.flush();
	    bw.close();
	    
	    //testing purposes
	    System.out.println("recents updated");
	}
}
