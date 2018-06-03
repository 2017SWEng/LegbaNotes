/**
 * 
 */
package com.legba.notes.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.legba.notes.elements.User.USER_TYPE;

import java.io.File;

/**
 * This class represents a module that the students are studying.
 * @author jjds502
 * 
 */
@XmlRootElement(name="module")
@XmlAccessorType(XmlAccessType.NONE)
public class Module {

	@XmlElement(name="title")
	private String title;
	
	@XmlElement(name="year")
	private int year;
	
	@XmlElement(name="content", nillable=true)
	private List<File> content;
	
	@XmlElement(name="assignedStudents", nillable=true)
	private List<User> assignedStudents;
	
	@XmlElement(name="assignedLecturers", nillable=true)
	private List<User> assignedLecturers;
	
	public Module(String title, int year){
		this.setTitle(title);
		this.setYear(year);
		
		//initialise the lists
		this.content = new ArrayList<File>();
		this.assignedStudents = new ArrayList<User>();
		this.assignedLecturers = new ArrayList<User>();
	}

	public Module(){
		this.setTitle("Please Set Title");
		this.setYear(0);
		
		//initialise the lists
		this.content = new ArrayList<File>();
		this.assignedStudents = new ArrayList<User>();
		this.assignedLecturers = new ArrayList<User>();
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
	
	@Override
	public String toString() {
		/**
		 * ToString Overridden to return Module in readable formal
		 * @author jjds502
		 * @Return Title and Years
		 */
		return "Module Title: " + this.title + System.lineSeparator() +
		"Course year: " + this.year; 
		
	}
}
