package com.legba.notes.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import com.legba.notes.elements.Presentation;
import com.legba.notes.models.AppModel;
import com.legba.notes.models.ViewMode;
import com.legba.notes.models.ViewMode.Mode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;

public class MenuController implements Observer{

	
	@FXML
	BorderPane topbar_root;
	
	@FXML
	Button HomeBtn;
	
	@FXML
	MenuBar menuBar;
	
	@FXML
	ImageView homeLogo;
	
	private final static String toolbarPath = "../fxml/toolbar.fxml";

	public MenuController(){
	}
	
	@FXML
    void initialize(){
	
		// Add veiwMode observer
		AppModel.getInstance().addVeiwModeObserver(this);
		
		// force updateMode to load default page
		updateMode(ViewMode.Mode.HOMEPAGE);
	}
	
	@FXML
	protected void handleHomeButtonAction(MouseEvent event) {
		 AppModel.getInstance().setVeiwMode(Mode.HOMEPAGE);
	}
	
	private void updateMode(Mode mode) {

		if (mode == Mode.VEIWING){
			switchToViewing();
		}
		else if (mode == Mode.HOMEPAGE){
			switchToHomepage();
		}
		else{
			System.err.println(this.toString() +" : uknown viewmode bailing to homepage" );
			switchToHomepage();

		}
	}

	
	private void switchToHomepage(){
		topbar_root.setBottom(null);
	}
	
	private void switchToViewing(){
		topbar_root.setBottom(loadFXML( getClass().getResource(toolbarPath)));
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

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("== app update ==");
		
		// check what has called this method
		if (o instanceof ViewMode){
			if (arg instanceof Mode){
				updateMode((Mode)arg);
			}
			else {
				System.err.println("[err!] Veiw mode passed unexpected object type to observers");
			}
		}
	}
}
