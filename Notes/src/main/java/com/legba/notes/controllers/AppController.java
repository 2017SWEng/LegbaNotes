package com.legba.notes.controllers;


import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com.legba.notes.models.AppModel;
import com.legba.notes.models.ViewMode;
import com.legba.notes.models.ViewMode.Mode;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.scene.Parent;

public class AppController implements Observer{
	
	@FXML
	private BorderPane root;
    
	private static AppController instance;
	
	public MenuController menu;
	public ViewingController viewing;
	public HomepageController homepage;
	
	private AppController(){
		// Called before all variable with @FXML have been populated
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
		AppModel.getInstance().addVeiwModeObserver(this);
		updateMode(ViewMode.Mode.HOMEPAGE);
	}
	
	private void addMenu() {
		
		System.out.println("[+] Loading Menu");
		System.out.println("\t path = " + getClass().getResource("../fxml/menu.fxml"));
		
		Parent menu = null;
		try {
			menu = FXMLLoader.load(getClass().getResource("../fxml/menu.fxml"));
		} catch (IOException e) {
			System.err.println("\t[ERR!] Unable to load menu");
			e.printStackTrace();
		}
		
		if (menu != null){
			root.setTop(menu);
			System.out.println("\t[ OK ] Set Menu");
		}
		
		System.out.println("[-] Loaded Menu");

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		System.out.println("== app update ==");
		
		if (arg0 instanceof ViewMode){
			if (arg1 instanceof Mode){
				updateMode((Mode)arg1);
			}
			else {
				System.err.println("[err!] Veiw mode passed unexpected object type to observers");
			}
		}
		
	}

	private void updateMode(Mode mode) {
				
		if (mode == Mode.HOMEPAGE){
			System.out.println("[+] Loading homepage");
			
			Parent homepage = null;
			try {
				homepage = FXMLLoader.load(getClass().getResource("../fxml/homepage.fxml"));
			} catch (IOException e) {
				System.err.println("\t[ERR!] Unable to load homepage");
				e.printStackTrace();
			}
			
			if (homepage != null){
				root.setCenter(homepage);
				System.out.println("\t[ OK ] Set homepage");
			}
			
			System.out.println("[-] Loaded homepage");
		}
		else if(mode == Mode.VEIWING){
			System.out.println("[+] Loading viewing");
			
			Parent veiwing = null;
			try {
				veiwing = FXMLLoader.load(getClass().getResource("../fxml/viewing.fxml"));
			} catch (IOException e) {
				System.err.println("\t[ERR!] Unable to load veiwing");
				e.printStackTrace();
			}
			
			if (veiwing != null){
				root.setCenter(veiwing);
				System.out.println("\t[ OK ] Set viewing");
			}
			
			System.out.println("[-] Loaded viewing");
		}
		
	}

}
