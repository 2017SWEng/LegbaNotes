package com.legba.notes.controllers;

import com.legba.notes.elements.Course;
import com.legba.notes.elements.Module;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ModuleManageFormController {

	ObservableList<String> ItemList;
	
	@FXML
	TextField ModuleNumberField;
	
	@FXML
	TextField ModuleTitleField;
	
	@FXML
	TextField CourseNumberField;
	
	@FXML
	TextField CourseTitleField;
	
	@FXML
	TextField UserForenameField;
	
	@FXML
	TextField UserSurnameField;
	
	@FXML
	TextField UsernameField;
	
	
	@FXML
	ListView<String> ExistingObjectList;
	
	@FXML public void handleNewModuleBtn(ActionEvent e){
		
		Module newModule = new Module(this.ModuleTitleField.getText(), Integer.parseInt(this.ModuleNumberField.getText()));
		ItemList.add(newModule.toString());
		
		FileSystemController fsc = new FileSystemController();
		fsc.saveToModulesFile(newModule);
	}
	
	@FXML public void handleNewCourseBtn(ActionEvent e){
		
		Course newCourse = new Course(this.CourseTitleField.getText(), Integer.parseInt(this.CourseNumberField.getText()));
		ItemList.add(newCourse.toString());
		
		FileSystemController fsc = new FileSystemController();
		fsc.saveToCourseFile(newCourse);
	}
	
	@FXML public void handleNewUserBtn(ActionEvent e){
		
//		Course newCourse = new Course(this.CourseTitleField.getText(), Integer.parseInt(this.CourseNumberField.getText()));
//		ItemList.add(newCourse.toString());
//		
//		FileSystemController fsc = new FileSystemController();
//		fsc.saveToCourseFile(newCourse);
	}

	@FXML public void handleAddModuleBtn(ActionEvent e){
		
	}
	
	public ModuleManageFormController(){
		
	}
	
	public void initialize() {
		
		/**
		 * 
		 * @author jjds502
		 */
		
		//to implement number box, simply add this listener to the required TextField
		// referenced from:
		//http://www.tutorialsface.com/2016/12/how-to-make-numeric-decimal-textfield-in-javafx-example-tutorial/
		
		ModuleNumberField.textProperty().addListener(new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				// \\d[0,7]? denotes digits of length 0 to 7, to change length simply change the 7 to the required value
				//To add decimal values simply add ([\\.]\\d{0,n})? with n  being the required max length
				if (!newValue.matches("\\d{0,7}?")) {
					ModuleNumberField.setText(oldValue);
				}
			}
		});
		
		//create a list for the modules
		ItemList = ExistingObjectList.getItems();
	}
	
	
}
