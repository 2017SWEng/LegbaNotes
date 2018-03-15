package com.legba.notes.controllers;


import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import com.legba.notes.models.AppModel;
import com.legba.notes.models.ViewMode;
import com.legba.notes.models.ViewMode.Mode;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;

public class AppController implements Observer{
	
	@FXML
	public BorderPane root;
    
	private static AppController instance;
	
	public MenuController menu;
	public ViewingController viewing;
	public HomepageController homepage;
	public FileSystemController fileSystemController;
	
	private final static String menuPath = "../fxml/menu.fxml";
	private final static String homepagePath = "../fxml/homepage.fxml";
	private final static String viewingPath = "../fxml/viewing.fxml";
	
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
		Node menu = loadFXML( getClass().getResource(menuPath));
		
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
		
		logNodes(root,0);

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
		Node n = loadFXML( getClass().getResource(path));
		
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
	
}
