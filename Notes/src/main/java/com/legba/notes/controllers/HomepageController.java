package com.legba.notes.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.legba.notes.elements.Presentation;
import com.legba.notes.models.AppModel;
import com.legba.notes.models.ViewMode.Mode;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class HomepageController {

	@FXML
	ListView<String> contentList;
	
	@FXML
	Button fileChooserBtn;
	
	@FXML
	Button LoginTest;
	
	@FXML
	Button formsBtn;
	
	@FXML
	ListView<String> recentsList;
	
	@FXML public void GoToLogin(ActionEvent event){
		AppModel.getInstance().setVeiwMode(Mode.LOGIN);
	}
	
	@FXML public void GoToForm(ActionEvent event){
		AppModel.getInstance().setVeiwMode(Mode.MODULE_MANAGEMENT);
	}
	@FXML public void handleContentListClick(MouseEvent event) {
		
		try{
			//Change view to viewer and render the selected Object(s)
			FileSystemController fsc = new FileSystemController();
			//get the file selected from the list
			Presentation pres = fsc.loadXmlFile(contentList.getSelectionModel().getSelectedItem());	
			//if chosen the file should be rendered in the view mode
			AppModel.getInstance().setPres(pres);
			AppModel.getInstance().setVeiwMode(Mode.VEIWING);
			
			//Output to console
			System.out.println("selected " + contentList.getSelectionModel().getSelectedItem());
			
			try {
				File file = new File(contentList.getSelectionModel().getSelectedItem());
				AppController.getInstance().updateRecents(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to update Recent Docs");
				e.printStackTrace();
			}
		}
		catch (NullPointerException e) {
			//if it fails, ramain in the homepage mode and then ask for a file
			System.out.println("Please select a file...");
		}
		
	}
@FXML public void handleRecentsListClick(MouseEvent event) {
		
		try{
			//Change view to viewer and render the selected Object(s)
			FileSystemController fsc = new FileSystemController();
			//get the file selected from the list
			Presentation pres = fsc.loadXmlFile(recentsList.getSelectionModel().getSelectedItem());	
			//if chosen the file should be rendered in the view mode
			AppModel.getInstance().setPres(pres);
			AppModel.getInstance().setVeiwMode(Mode.VEIWING);
			
			//Output to console
			System.out.println("selected " + recentsList.getSelectionModel().getSelectedItem());
			
			try {
				File file = new File(recentsList.getSelectionModel().getSelectedItem());
				AppController.getInstance().updateRecents(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to update Recent Docs");
				e.printStackTrace();
			}
		}
		catch (NullPointerException e) {
			//if it fails, ramain in the homepage mode and then ask for a file
			System.out.println("Please select a file...");
		}
		
	}
	
	@FXML public void handleNewFileBtn(ActionEvent event){

		//Output to console
		System.out.println("Creating new File...");
		
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save New Note File");
        fileChooser.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("Text file", "*.pws"),
        		new FileChooser.ExtensionFilter("Text File", "*.node")
        );
        
        //get the file
        File file = fileChooser.showSaveDialog(AppController.getInstance().getMainStage());
        
        //save the file
        if (file != null) {   
        	try {
        		//check that the file extension is correct. Referenced from:
        		//https://stackoverflow.com/questions/17010647/set-default-saving-extension-with-jfilechooser
        		
        		//if the file doesn't exist, create a new one
        		if (!file.exists()) {
        			file.createNewFile();
        		}
        		//now we need to create the file
        		FileWriter fw = new FileWriter(file.getAbsoluteFile());
        	    BufferedWriter bw = new BufferedWriter(fw);
        	    //add some basic tags so it can be read
        	    bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        	    bw.write("<Presentation xmlns=\"pws\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"pws schema.xsd\" italic=\"false\" bold=\"false\" underline=\"false\" textsize=\"12\" color=\"#F341DE\" font=\"Times New Roman\">");
        	    bw.write("<Meta key=\"author\" value=\"user\" />");
        	    bw.write("</Presentation>");
        	    bw.flush();
        	    bw.close();
        	    
        	    //use save xmlFile() to save directly to the correct format
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    		System.out.println("Created new File");

        }
		
	}
	
	@FXML public void handleFileChooserBtn(ActionEvent event){
		// referenced from
		// https://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
		
		//Output to console
		System.out.println("Open File Chooser...");
		
		//Open and format the file viewer
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Document");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Presentation", "*.pws"),
				new FileChooser.ExtensionFilter("All Nodes", "*.node")
		);
		
		File fileToOpen = fileChooser.showOpenDialog(AppController.getInstance().getMainStage());
        if (fileToOpen != null) {
        	//Change view to viewer and render the selected Object(s)
    		FileSystemController fsc = new FileSystemController();
    		Presentation pres = fsc.loadXmlFile(fileToOpen.toString());
    		AppModel.getInstance().setPres(pres);
    		AppModel.getInstance().setVeiwMode(Mode.VEIWING);
    		
    		//!!!! place ^^^^^^^ into its own module of code
    		//add to recent documents
    		try {
				AppController.getInstance().updateRecents(fileToOpen);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to update Recent Docs");
				e.printStackTrace();
			}
        }
	}
	
	public HomepageController(){
		
	}
	
	public void initialize() {
		
		//load some list example documents
		
		//----------------------------------------------------------//
		// Will need to check for a Legba Notes directory and then  //
		// create one if there is not one present.					//
		// Written by jjds502											//
		//----------------------------------------------------------//
		
		//lets put some things in our documents list
		ArrayList<File> files = new ArrayList<File>();
		files.add(new File("example.pws"));
		files.add(new File("example2.pws"));
		files.add(new File("/Notes/src/main/resources/com/legba/notes/PDF/pdf.html"));
		

		ObservableList<String> fileItems = contentList.getItems();

		for(int i = 0; i < files.size(); i++ ){
			fileItems.add(files.get(i).toString());
		}	
		
		//initialise the recents list also
		ObservableList<String> recentItems = recentsList.getItems();
		
		//check to see if file exists
		try{
			File recentsFile = new File(System.getProperty("user.home") + File.separator + "Legba" + File.separator + "RecentDocs");
			BufferedReader br = new BufferedReader(new FileReader(recentsFile.toString()));
			
			try {
			    String line = br.readLine();
			    while (line != null) {
			    	recentItems.add(line);
			        line = br.readLine();
			    }
			} finally {
			    br.close();
			}
		}
		catch(IOException ioe){
			recentItems.add("No Recent Files");
		}
		
		
		System.out.println("Home Page Initialised");
		
	}
	
	
}
