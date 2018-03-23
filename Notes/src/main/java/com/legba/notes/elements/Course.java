/**
 * 
 */
package com.legba.notes.elements;

import java.util.List;
import java.util.ArrayList;
import com.legba.notes.elements.Module;
import com.legba.notes.elements.User.USER_TYPE;

/**
 * @author jjds502
 *
 */
public class Course {

	enum COURSE_YEAR {
		FIRST_YEAR, SECOND_YEAR, THIRD_YEAR, FOURTH_YEAR 
	};
	private String title;
	private List<User> assignedStudents;
	//private List<User> assignedLecturers;
	private ArrayList<ArrayList<Module>> yearlyModules;
	
	public Course(String title, int years){
		
		this.title = title;
		yearlyModules = new ArrayList<ArrayList<Module>>(years);
		
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
}
