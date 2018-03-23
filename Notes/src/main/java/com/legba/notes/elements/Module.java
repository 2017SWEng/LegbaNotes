/**
 * 
 */
package com.legba.notes.elements;

import java.util.Collections;
import java.util.List;

import com.legba.notes.elements.User.USER_TYPE;

import java.io.File;

/**
 * This class represents a module that the students are studying.
 * @author jjds502
 * 
 */
public class Module {

	private String title;
	private int year;
	private List<File> content;
	private List<User> assignedStudents;
	private List<User> assignedLecturers;
	
	public Module(String title, int year){
		this.setTitle(title);
		this.setYear(year);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void addFile(File file){
		this.content.add(file);
	}
	
	public void sortContent(){
		/**
		 * Automatically sort the files
		 * @param None
		 * @return None
		 */
		Collections.sort(this.content);
	}
	
	public List<File> getContentList(){
		/**
		 * Use to return a full list of the content associated to the module.
		 * @param None
		 * @return List<File> containing all files within this module
		 */
		return this.content;
	}
	
	public void addStudent(User student){
		
		//check to see if the user is actually a student
		if(student.getUserType() == USER_TYPE.STUDENT){
			this.assignedStudents.add(student);
		} else {
			System.out.println("User_Type must be Student for " + student.toString());
		}
		
	}
	
	public void addStudent(List<User> studentList){
		
		User student = null;
		
		for(int i = 0; i < studentList.size(); i++){
			student = studentList.get(i);
			//check to see if the user is actually a student
			if(student.getUserType() == USER_TYPE.STUDENT){
				this.assignedStudents.add(student);
			} else {
				System.out.println("User_Type must be Student for " + student.toString());
			}
		}	
				
	}
}
