/**
 * 
 */
package com.legba.notes.elements;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import com.legba.notes.elements.Module;
import com.legba.notes.elements.User.USER_TYPE;

/**
 * @author jjds502
 *
 */
//xml markup
@XmlRootElement(name="course")
@XmlAccessorType(XmlAccessType.NONE)
public class Course {

	enum COURSE_YEAR {
		FIRST_YEAR, SECOND_YEAR, THIRD_YEAR, FOURTH_YEAR 
	};
	
	@XmlElement(name="title")
	private String title;
	
	@XmlElement(name="years")
	private int years;
	
	@XmlElement(name="assignedStudents", nillable=true)
	private List<User> assignedStudents;
	
	//private List<User> assignedLecturers;
	
	@XmlElement(name="yearlyModules", nillable=true)
	private ArrayList<ArrayList<Module>> yearlyModules;
	
	public Course(String title, int years){
		
		this.title = title;
		this.years = years;
		
		//instantiate lists
		this.yearlyModules = new ArrayList<ArrayList<Module>>(years);
		this.assignedStudents = new ArrayList<User>();
	}
	
	public Course(){
		this.setTitle("Please Set Title");
		
		//instantiate lists
		this.yearlyModules = new ArrayList<ArrayList<Module>>(years);
		this.assignedStudents = new ArrayList<User>();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDuration() {
		return years;
	}
	public void setDuration(int years) {
		this.years = years;
	}
	public void addModule(String title, int year){
		this.yearlyModules.get(year-1).add(new Module(title, year));
	}
	public boolean removeModule(Module module){
		/**
		 * Will remove the instance of the provided module from the list of modules
		 * associated with this course
		 * @param The Module to be removed (as object)
		 * @return True if the item has been found and removed - False if the item is not in the List
		 */
		return this.yearlyModules.get(module.getYear()).remove(module);
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
		return "Course Title: " + this.title + System.lineSeparator() +
				"Duration: " + this.years + " years";
		
	}
	
}
